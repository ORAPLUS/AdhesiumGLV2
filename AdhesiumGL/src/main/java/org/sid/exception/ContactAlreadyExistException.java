package org.sid.exception;

public class ContactAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContactAlreadyExistException(String message) {
		super(message);
	}
}
