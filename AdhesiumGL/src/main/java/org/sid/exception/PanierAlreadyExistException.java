package org.sid.exception;

public class PanierAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PanierAlreadyExistException(String message) {
		super(message);
	}
}
