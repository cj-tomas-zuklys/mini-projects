package net.codinginaction.mp.sbrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:${catalina.base}/conf/spring-boot-restapi/spring-boot-restapi.properties")
public class ProductionConfig {

}
