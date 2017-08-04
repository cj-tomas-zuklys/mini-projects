package net.codinginaction.mp.sbrestapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7653335292795429056L;

	public UserNotFoundException(Long id) {
		super("could not find user by id: " + id);
	}
}
