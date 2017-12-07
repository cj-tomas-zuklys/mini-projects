package net.codinginaction.springcloudminiproject.restapi;


import net.codinginaction.springcloudminiproject.jpa.ReservationRepository;
import net.codinginaction.springcloudminiproject.jpa.orm.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RefreshScope
public class ReservationRestControllerClassic {

    @Autowired
    public ReservationRestControllerClassic(@Value("${message}") String message) {
        this.message = message;
    }

    @Autowired
    private ReservationRepository reservationRepository;

    private String message;



        @RequestMapping(method = RequestMethod.GET, value = "/reservations-classic")
    public Collection<Reservation> reservations() {
        return this.reservationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message")
    public String readMessageValue() {
        return this.message;
    }

}
