package com.hiberus.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class UserModificationException extends RuntimeException {

	public UserModificationException(String exception) {
		super(exception);
	}

}
