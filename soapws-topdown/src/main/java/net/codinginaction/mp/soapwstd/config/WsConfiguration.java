package net.codinginaction.mp.soapwstd.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import net.codinginaction.mp.soapwstd.service.impl.GreeterImpl;

@Configuration
@ComponentScan("net.codinginaction.mp.soapwstd")
public class WsConfiguration {

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
		EndpointImpl endpoint = new EndpointImpl(springBus(), new GreeterImpl());
		endpoint.setAddress("/greeter");
		endpoint.publish();
		return endpoint;
	}

}
