package org.sid.entities.Exception;

@SuppressWarnings("serial")
public class CompteIntrouvableException extends Exception {

	public CompteIntrouvableException() {
		super("Compte introuvable!!! Verifiez bien le code compte que vous avez saisi!!!");
	}

	public CompteIntrouvableException(String message) {
		super(message);
	}

}
