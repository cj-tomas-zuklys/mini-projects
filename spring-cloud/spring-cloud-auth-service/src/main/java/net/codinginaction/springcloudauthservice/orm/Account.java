package net.codinginaction.springcloudauthservice.orm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@ToString
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private boolean active;

    public Account() {

    }

    public Account(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }


}
