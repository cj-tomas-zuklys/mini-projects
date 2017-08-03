package net.codinginaction.mp.sbsoapws.config;

import javax.servlet.Filter;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import net.codinginaction.mp.sbsoapws.filters.ProcessingTimeFilter;
import net.codinginaction.mp.sbsoapws.filters.RequestLoggingFilter;
import net.codinginaction.mp.sbsoapws.filters.RequestTrackingFilter;
import net.codinginaction.mp.sbsoapws.service.BottomUpWs;

@Configuration
@PropertySource("classpath:spring-boot-soapws.properties")
@ComponentScan("net.codinginaction.mp.sbsoapws")
public class WsConfiguration {

	@Autowired
	private BottomUpWs bottomUpWs;

	@Bean
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), bottomUpWs);
		endpoint.setAddress("/bottom-up-ws");
		endpoint.publish();
		return endpoint;
	}

	@Bean
	public FilterRegistrationBean requestTrackingFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(requestTrackingFilter());
		registration.addUrlPatterns("/*");
		registration.setName("request-tracking-filter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public Filter requestTrackingFilter() {
		return new RequestTrackingFilter();
	}

	@Bean
	public FilterRegistrationBean processingTimeFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(processingTimeFilter());
		registration.addUrlPatterns("/*");
		registration.setName("processing-time-filter");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public Filter processingTimeFilter() {
		return new ProcessingTimeFilter();
	}

	@Bean
	public FilterRegistrationBean requestLoggingFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(requestLoggingFilter());
		registration.addUrlPatterns("/*");
		registration.setName("request-logging-filter");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public Filter requestLoggingFilter() {
		return new RequestLoggingFilter();
	}
}
