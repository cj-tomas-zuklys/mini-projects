package net.codinginaction.springcloudzipkinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinStreamServer
public class SpringCloudZipkinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZipkinServiceApplication.class, args);
	}
}
