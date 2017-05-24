package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
//Pour signaler une entité correspondant à une table en base
@Entity
//L'heritage sera géré dans une seule table 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//La classe mère correspondra à une table ayant une colonne [TYPE_OP] pour distinguer la classe fille correspondante
@DiscriminatorColumn(name="TYPE_OP", discriminatorType=DiscriminatorType.STRING, length=1)
public abstract class Operation implements Serializable {
	//Primary key auto-increment
	@Id @GeneratedValue
	private Long numero;
	private Date dateOperation;
	private double montant;
	//Une compte peut avoir plusieurs operations
	@ManyToOne
	@JoinColumn(name="CODE_CPTE")
	private Compte compte;
	
	public Operation() {
		super();
	}

	public Operation(Date dateOperation, double montant, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
}
