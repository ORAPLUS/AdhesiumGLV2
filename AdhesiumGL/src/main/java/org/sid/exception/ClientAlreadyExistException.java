package org.sid.exception;

public class ClientAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientAlreadyExistException(String message) {
		super(message);
	}
}
