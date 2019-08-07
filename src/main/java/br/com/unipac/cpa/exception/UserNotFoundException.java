package br.com.unipac.cpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O usuario já existe")
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6469007988060422304L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
