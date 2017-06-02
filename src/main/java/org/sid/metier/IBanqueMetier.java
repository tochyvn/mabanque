package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Exception.CompteIntrouvableException;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	
	public Compte consulterCompte(String codeCpte) throws CompteIntrouvableException;
	public void verser(String codeCpte, double montant) throws CompteIntrouvableException;
	public void retirer(String codeCpte, double montant) throws Exception;
	public void virement(String codeCpte1, String codeCpte2, double montant) throws Exception;
	public Page<Operation> listOperation(String codeCompte, int page, int size);

}
