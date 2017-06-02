package org.sid.entities.Exception;

@SuppressWarnings("serial")
public class CompteVirementException extends Exception {

	public CompteVirementException() {
		super("Veuillez selectionner un compte emetteur et compte recepteur!!!");
	}

	public CompteVirementException(String message) {
		super(message);
	}
	
	
}
