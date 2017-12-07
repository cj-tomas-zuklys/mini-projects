package net.codinginaction.springcloudminiproject;

import net.codinginaction.springcloudminiproject.messaging.ReservationServiceChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(ReservationServiceChannel.class)
public class SpringCloudMiniProjectApplication {

    private String test2;

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudMiniProjectApplication.class, args);

    }


}