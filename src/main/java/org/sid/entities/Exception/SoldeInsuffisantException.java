package org.sid.entities.Exception;

@SuppressWarnings("serial")
public class SoldeInsuffisantException extends Exception {

	public SoldeInsuffisantException() {
		super("Solde insuffisant pour effectuer l'opération!!!");
	}

	public SoldeInsuffisantException(String message) {
		super(message);
	}
	
}
