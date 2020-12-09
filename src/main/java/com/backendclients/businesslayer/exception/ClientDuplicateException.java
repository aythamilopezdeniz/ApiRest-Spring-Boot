package com.backendclients.businesslayer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ClientDuplicateException extends RuntimeException {

	public ClientDuplicateException(String exception) {
		super(exception);
	}
}