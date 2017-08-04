package net.codinginaction.mp.sbrestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class Configuration {

	@Value("${validation.user.max-username-length:20}")
	private int userMaxUsernameLength;

	@Value("${validation.user.firstname-max-length:50}")
	private int userMaxFirstnameLength;

	@Value("${validation.user.lastname-max-length:50}")
	private int userMaxLastnameLength;

	@Value("${validation.user.password-max-length:50}")
	private int userMaxPassswordLength;

}
