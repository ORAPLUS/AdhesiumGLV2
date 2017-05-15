package org.sid.exception;

public class PanierNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PanierNotFoundException(String message) {
		super(message);
	}

}
