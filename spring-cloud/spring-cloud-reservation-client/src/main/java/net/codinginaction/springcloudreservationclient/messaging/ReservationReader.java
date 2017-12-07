package net.codinginaction.springcloudreservationclient.messaging;

import net.codinginaction.springcloudreservationclient.data.Reservation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("reservation-service")
public interface ReservationReader {

    @RequestMapping(method = RequestMethod.GET, value = "/reservations-via-spring-data")
    Resources<Reservation> read();

}
