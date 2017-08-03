package net.codinginaction.mp.soapwsbu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:${catalina.base}/conf/soapws-bottomup/soapws-bottomup.properties")
public class ProductionConfig {

}
