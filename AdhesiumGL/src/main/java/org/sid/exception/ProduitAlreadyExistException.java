package org.sid.exception;

public class ProduitAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProduitAlreadyExistException(String message) {
		super(message);
	}
}
