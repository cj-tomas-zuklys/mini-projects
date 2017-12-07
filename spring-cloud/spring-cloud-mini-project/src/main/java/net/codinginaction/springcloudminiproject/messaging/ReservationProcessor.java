package net.codinginaction.springcloudminiproject.messaging;

import net.codinginaction.springcloudminiproject.jpa.ReservationRepository;
import net.codinginaction.springcloudminiproject.jpa.orm.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class ReservationProcessor {

    @ServiceActivator(inputChannel = "input")
    public void onNewReservations(Message<String> msg) {
        this.reservationRepository.save(new Reservation(msg.getPayload()));
    }

    @Autowired
    private ReservationRepository reservationRepository;

}
