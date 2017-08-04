package net.codinginaction.mp.sbrestapi.config;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String DEFAULT_SPRING_PROFILE = "dev";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("onStartup. ");
		String profile = getActiveProfile();
		servletContext.setInitParameter("spring.profiles.active", profile);
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(RestApiConfiguration.class);
		context.setServletContext(servletContext);
		context.refresh();
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

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RestApiConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/*"};
	}

}
