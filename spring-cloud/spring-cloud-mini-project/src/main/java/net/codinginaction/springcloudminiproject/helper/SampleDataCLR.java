package net.codinginaction.springcloudminiproject.helper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.codinginaction.springcloudminiproject.jpa.ReservationRepository;
import net.codinginaction.springcloudminiproject.jpa.orm.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class SampleDataCLR implements CommandLineRunner {

    @Autowired
    public SampleDataCLR(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Tomas", "Jack", "Bill", "Josh", "Den", "Ben", "Sam", "Rock")
                .forEach(name -> reservationRepository.save(new Reservation(name)));
        List<Reservation> list = reservationRepository.findAll();
        for (Reservation r : list) {
            log.debug("{}", r);
        }
    }
}
