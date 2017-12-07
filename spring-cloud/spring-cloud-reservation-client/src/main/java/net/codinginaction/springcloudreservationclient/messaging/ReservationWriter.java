package net.codinginaction.springcloudreservationclient.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ReservationWriter {

    @Gateway(requestChannel = "output")
    void write(String rn);

}
