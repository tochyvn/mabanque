package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private double decouvert;

	public CompteCourant() {
		super();
	}

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(codeCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	public boolean ifPossible(double montant) {
		double authorizedAmount = this.solde + this.decouvert;
		boolean authorization = true;
		if (montant > authorizedAmount) {
			authorization = false;
		}
		return authorization;
	}

	@Override
	public double getMaxRetrait() {
		return this.solde + decouvert;
	}
	
	
	
}
