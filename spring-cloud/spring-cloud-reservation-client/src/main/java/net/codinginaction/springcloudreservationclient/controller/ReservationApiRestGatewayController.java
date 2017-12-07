package net.codinginaction.springcloudreservationclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.codinginaction.springcloudreservationclient.messaging.ReservationClientChannels;
import net.codinginaction.springcloudreservationclient.data.Reservation;
import net.codinginaction.springcloudreservationclient.messaging.ReservationReader;
import net.codinginaction.springcloudreservationclient.messaging.ReservationWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("reservations")
public class ReservationApiRestGatewayController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservationReader reservationReader;

    @Autowired
    private ReservationWriter reservationWriter;

    private final MessageChannel out;

    @Autowired
    public ReservationApiRestGatewayController(ReservationClientChannels clientChannels) {
        this.out = clientChannels.output();
    }

    public Collection<String> fallback() {
        return new ArrayList<>();
    }

    ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() { };

    @RequestMapping(method = RequestMethod.GET, value = "/name")
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<String> names() {
        ResponseEntity<Resources<Reservation>> responseEntity = this.restTemplate.exchange("http://reservation-service/reservations-via-spring-data", HttpMethod.GET, null, ptr);
        return responseEntity.getBody().getContent().stream().map(reservation -> reservation.getReservationName()).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/names-using-feign")
    @HystrixCommand(fallbackMethod = "fallback")
    public Collection<String> namesUsingFeign() {
        return this.reservationReader.read().getContent().stream().map(reservation -> reservation.getReservationName()).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Reservation reservation) {
        Message<String> msg = MessageBuilder.withPayload(reservation.getReservationName()).build();
        this.out.send(msg);
    }

    @RequestMapping(method = RequestMethod.POST, value = "write-via-feign")
    public void writeViaFeign(@RequestBody Reservation reservation) {
        this.reservationWriter.write(reservation.getReservationName());
    }

}
