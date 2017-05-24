package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
	
	private double plafond;
	
	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client) {
		super(codeCompte, dateCreation, solde, client);
		setPlafond(10000);
	}
	
	public double getPlafond() {
		return plafond;
	}

	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}

	public boolean ifPossible(double montant) {
		double authorizedAmount = this.solde;
		boolean authorization = true;
		if (montant > authorizedAmount) {
			authorization = false;
		}
		return authorization;
	}

	@Override
	public double getMaxRetrait() {
		return this.solde;
	}
	
	
}
