package it.prova.pizzastorebackend.exception;

public class OrdineNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrdineNotFoundException() {
		super("Ordine ricercato non trovato");
		// TODO Auto-generated constructor stub
	}

	public OrdineNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
