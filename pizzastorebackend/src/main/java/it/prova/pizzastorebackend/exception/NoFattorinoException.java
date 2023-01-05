package it.prova.pizzastorebackend.exception;

public class NoFattorinoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoFattorinoException() {
		super("L'utente selezionato non e' un fantino");
		// TODO Auto-generated constructor stub
	}

}
