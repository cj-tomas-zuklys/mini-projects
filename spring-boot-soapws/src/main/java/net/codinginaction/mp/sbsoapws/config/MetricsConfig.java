package net.codinginaction.mp.sbsoapws.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMetrics
@Slf4j
public class MetricsConfig extends MetricsConfigurerAdapter {

	@Override
    public void configureReporters(MetricRegistry metricRegistry) {
		log.info("configureReporters. ");
        registerReporter(Slf4jReporter.forRegistry(metricRegistry)
        	.outputTo(log)
            .build())
            .start(1, TimeUnit.MINUTES);
    }

}
