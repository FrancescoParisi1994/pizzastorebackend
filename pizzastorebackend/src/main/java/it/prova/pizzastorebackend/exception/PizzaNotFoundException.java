package it.prova.pizzastorebackend.exception;

public class PizzaNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PizzaNotFoundException() {
		super("Pizza ricercata non trovata");
		// TODO Auto-generated constructor stub
	}

	public PizzaNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
