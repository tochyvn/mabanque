package org.sid.metier;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Pour spécifier à Spring de créer un bean au démarrage de l'application (injection de dependance) 
//Et sera chargé plus tard par un [Autowired]
@Service
//Demande à spring de mettre chacune des fonctions ici bas dans une transaction
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = compteRepository.findOne(codeCpte);
		if (cp == null) {
			throw new RuntimeException("Compte introuvable");
		}
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte compte = this.consulterCompte(codeCpte);
		if (compte != null) {
			Operation versement = new Versement(new Date(), montant, compte);
			//insertion du versement
			operationRepository.save(versement);
			compte.incrementer(montant);
			//Mise à jour du compte
			compteRepository.save(compte);
		} else {
			throw new RuntimeException("Le compte dans lequel vous voulez effectuer un retrait n'existe pas");
		}
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte compte = this.consulterCompte(codeCpte);
		if (compte != null) {
			if (! compte.ifPossible(montant)) {
				throw new RuntimeException("Solde insuffisant!!!! [Votre retrait max est de "
						+ "" + compte.getMaxRetrait() + " et vous voulez retirer " + montant + "]");
			} else {
				Operation retrait = new Retrait(new Date(), montant, compte);
				//Insertion du retrait
				operationRepository.save(retrait);
				//On met a jour le compte
				compte.decrementer(montant);
				compteRepository.save(compte);
			}
		} else {
			throw new RuntimeException("Le compte dans lequel vous voulez effectuer un retrait n'existe pas");
		}
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		Compte compteEmetteur = this.consulterCompte(codeCpte1);
		Compte compteRecepteur = this.consulterCompte(codeCpte2);
		if (compteEmetteur == null ||  compteRecepteur == null) {
			throw new RuntimeException("Veuillez selectionner un compte emetteur et un compte recepteur");
		} else {
			//On retire dans le compte emetteur
			this.retirer(codeCpte1, montant);
			//On verse dans le compte recepteur
			this.verser(codeCpte2, montant);
		}
	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		return operationRepository.getOperations(codeCompte, new PageRequest(page, size));
	}

}
