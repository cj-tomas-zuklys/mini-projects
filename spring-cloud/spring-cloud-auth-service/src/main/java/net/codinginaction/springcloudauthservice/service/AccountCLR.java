package net.codinginaction.springcloudauthservice.service;

import net.codinginaction.springcloudauthservice.orm.Account;
import net.codinginaction.springcloudauthservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AccountCLR implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("jlong,spring", "pweb,boot", "dsyer,cloud").map(s -> s.split(","))
                .forEach(tuple -> accountRepository.save(new Account(tuple[0], tuple[1], true)));
    }
}
