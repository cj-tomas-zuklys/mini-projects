package net.codinginaction.mp.sbrestapi.config;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.codinginaction.mp.sbrestapi.filters.ProcessingTimeFilter;
import net.codinginaction.mp.sbrestapi.filters.RequestLoggingFilter;
import net.codinginaction.mp.sbrestapi.filters.RequestTrackingFilter;

@Configuration
@ComponentScan("net.codinginaction.mp.sbrestapi")
@EnableWebMvc
public class RestApiConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    converters.add(new MappingJackson2HttpMessageConverter(getObjectMapper()));
	}

	@Bean
	public FilterRegistrationBean requestTrackingFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(requestTrackingFilter());
		registration.addUrlPatterns("/*");
		registration.setName("request-tracking-filter");
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
		return registration;
	}

	@Bean
	public Filter requestLoggingFilter() {
		return new RequestLoggingFilter();
	}

	@Override
	@Profile("dev")
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
