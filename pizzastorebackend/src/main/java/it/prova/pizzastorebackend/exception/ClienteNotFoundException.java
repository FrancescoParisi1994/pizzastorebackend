package it.prova.pizzastorebackend.exception;

public class ClienteNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException() {
		super("Cliente ricercato non trovato");
		// TODO Auto-generated constructor stub
	}

	public ClienteNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
