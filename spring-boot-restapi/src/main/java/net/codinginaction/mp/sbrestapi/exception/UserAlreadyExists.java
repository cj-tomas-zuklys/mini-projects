package net.codinginaction.mp.sbrestapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 7653335292795429056L;

	public UserAlreadyExists(String username) {
		super("User: " + username + " already exists");
	}
}
