package org.sid.handler;

import javax.servlet.http.HttpServletRequest;
import org.sid.exception.*;
import org.sid.entities.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	// Client
	@ExceptionHandler(ClientAlreadyExistException.class)
	public ResponseEntity<Error> handlerClientAlreadyExistException(ClientAlreadyExistException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Client Already Exist.", 1001L, System.currentTimeMillis(), "http://error.teste.com/1001", e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Error> handlerClientNotFoundException(ClientNotFoundException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Client Not Found.", 1002L, System.currentTimeMillis(), "http://error.teste.com/1002", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	// Contact
		@ExceptionHandler(ContactAlreadyExistException.class)
		public ResponseEntity<Error> handlerContactAlreadyExistException(ContactAlreadyExistException e, HttpServletRequest request) {
			e.printStackTrace();
			Error error = new Error("Contact Already Exist.", 1003L, System.currentTimeMillis(), "http://error.teste.com/1003", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		@ExceptionHandler(ContactNotFoundException.class)
		public ResponseEntity<Error> handlerContactNotFoundException(ContactNotFoundException e, HttpServletRequest request) {
			e.printStackTrace();
			Error error = new Error("Contact Not Found.", 1004L, System.currentTimeMillis(), "http://error.teste.com/1004",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	// Panier
	@ExceptionHandler(PanierAlreadyExistException.class)
	public ResponseEntity<Error> handlerPanierAlreadyExistException(PanierAlreadyExistException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Panier Already Exist.", 1005L, System.currentTimeMillis(), "http://error.teste.com/1005", e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(PanierNotFoundException.class)
	public ResponseEntity<Error> handlerPanierNotFoundException(PanierNotFoundException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Panier Not Found.", 1006L, System.currentTimeMillis(), "http://error.teste.com/1006", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	// Produit
	@ExceptionHandler(ProduitAlreadyExistException.class)
	public ResponseEntity<Error> handlerProduitAlreadyExistException(ProduitAlreadyExistException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Produit Already Exist.", 1007L, System.currentTimeMillis(), "http://error.teste.com/1007", e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(ProduitNotFoundException.class)
	public ResponseEntity<Error> handlerProduitNotFoundException(ProduitNotFoundException e, HttpServletRequest request) {
		e.printStackTrace();
		Error error = new Error("Produit Not Found.", 1008L, System.currentTimeMillis(), "http://error.teste.com/1008", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
