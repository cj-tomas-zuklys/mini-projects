package net.codinginaction.mp.soapwstd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:${catalina.base}/conf/soapws-topdown/soapws-topdown.properties")
public class ProductionConfig {

}
