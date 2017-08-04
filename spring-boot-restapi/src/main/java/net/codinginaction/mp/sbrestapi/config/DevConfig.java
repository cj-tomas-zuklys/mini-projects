package net.codinginaction.mp.sbrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:spring-boot-restapi.properties")
public class DevConfig {

}
