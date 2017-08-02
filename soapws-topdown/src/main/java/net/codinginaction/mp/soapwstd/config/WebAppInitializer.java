package net.codinginaction.mp.soapwstd.config;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.soapwstd.filters.ProcessingTimeFilter;
import net.codinginaction.mp.soapwstd.filters.RequestLoggingFilter;
import net.codinginaction.mp.soapwstd.filters.RequestTrackingFilter;

@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {

	private static final String DEFAULT_SPRING_PROFILE = "dev";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("onStartup. ");
		String profile = getActiveProfile();
		servletContext.setInitParameter("spring.profiles.active", profile);
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WsConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(context));
		registerFilters(servletContext);
		registerServlets(servletContext);
	}

	private void registerServlets(ServletContext servletContext) {
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("soapws-topdown-servlet", new CXFServlet());
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/ws/*");
	}

	private void registerFilters(ServletContext servletContext) {
		FilterRegistration.Dynamic requestTrackingFilter = servletContext.addFilter("request-tracking-filter", new RequestTrackingFilter());
		requestTrackingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

		FilterRegistration.Dynamic processingTimeFilter = servletContext.addFilter("processing-time-filter", new ProcessingTimeFilter());
		processingTimeFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

		FilterRegistration.Dynamic requestLoggingFilter = servletContext.addFilter("request-logging-filter", new RequestLoggingFilter());
		requestLoggingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

	private String getActiveProfile() {
		String profile = DEFAULT_SPRING_PROFILE;
		try {
			URL resource = this.getClass().getResource("/spring.active.profile");
			if (resource != null) {
				byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
				profile = new String(bytes);
			} else {
				log.warn("enableProfiles. no resource was found. will use '{}' profile", profile);
			}
			log.debug("getActiveProfile. profile: {}", profile);
		} catch (Exception e) {
			log.error("getActiveProfile. ", e);
		}
		return profile;
	}

}
