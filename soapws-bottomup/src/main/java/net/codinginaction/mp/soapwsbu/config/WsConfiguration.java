package net.codinginaction.mp.soapwsbu.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import net.codinginaction.mp.soapwsbu.service.BottomUpWs;

@Configuration
@ComponentScan("net.codinginaction.mp.soapwsbu")
public class WsConfiguration {

	@Autowired
	private BottomUpWs bottomUpWs;

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

}
