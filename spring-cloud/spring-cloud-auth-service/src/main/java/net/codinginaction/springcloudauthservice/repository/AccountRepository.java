package net.codinginaction.springcloudauthservice.repository;

import net.codinginaction.springcloudauthservice.orm.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByUsername(String username);
}
