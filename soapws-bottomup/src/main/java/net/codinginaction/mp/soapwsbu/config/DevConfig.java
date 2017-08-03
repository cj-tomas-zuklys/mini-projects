package net.codinginaction.mp.soapwsbu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:soapws-bottomup.properties")
public class DevConfig {

}
