package it.prova.pizzastorebackend.exception;

public class IdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdNotFoundException() {
		super("Id non trovato nella richiesta");
		// TODO Auto-generated constructor stub
	}

}
