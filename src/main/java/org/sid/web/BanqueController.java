package org.sid.web;

import java.util.ArrayList;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Exception.CompteIntrouvableException;
import org.sid.entities.Exception.SoldeInsuffisantException;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/gestion-de-comptes")
public class BanqueController {

	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/")
	public String index() {
		return "comptes";
	}

	@RequestMapping(value="/operations")
	public String operation() {
		return "comptes";
	}

	@RequestMapping("/comptes")
	public String compte() {
		return "comptes";
	}

	@RequestMapping("/clients")
	public String client() {
		return "clients";
	}

	@RequestMapping(value="/consulter-compte")
	public String consulter(Model model, String codeCompte) {
		
		if (codeCompte != null) {
			try {
				Compte cp = banqueMetier.consulterCompte(codeCompte);
				ArrayList<Operation> operations = new ArrayList<>(cp.getOperations());
				model.addAttribute("compte", cp);
				model.addAttribute("operations", operations);
			} catch (CompteIntrouvableException e) {
				model.addAttribute("exception", e.getMessage());
			}
		}

		return "comptes";
	}

	@RequestMapping(value="/transaction", method=RequestMethod.POST)
	public String transaction(Model model, String codeCompte, String typeOperation, String montant, String destinataire) {
		
		double montant_double = 0.0;
		
		if (codeCompte != null) {
			
			Compte selected_account = null;
			try {
				selected_account = banqueMetier.consulterCompte(codeCompte);
			} catch (CompteIntrouvableException e) {
				model.addAttribute("exception", e.getMessage());
			}
			try {
				montant_double = Double.parseDouble(montant);
			} catch (NumberFormatException e) {
				model.addAttribute("exception", "Le montant doit être au format numérique!!!");
			}
			
			if (typeOperation.equals("versement")) {
				try {
					banqueMetier.verser(codeCompte, montant_double);
				} catch (CompteIntrouvableException e) {
					model.addAttribute("exception", e.getMessage());
				}
			} else if (typeOperation.equals("retrait")) {
				try {
					banqueMetier.retirer(codeCompte, montant_double);
				} catch (Exception e) {
					System.out.println("==================  " + e.getMessage() + "  ======================");
					model.addAttribute("exception", e.getMessage());
				}
			} else {
				System.out.println("virementttttttt");
				try {
					Compte target_account = banqueMetier.consulterCompte(destinataire);
					banqueMetier.virement(codeCompte, target_account.getCodeCompte(), montant_double);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					model.addAttribute("exception", e.getMessage());
				}
			}
			model.addAttribute("compte", selected_account);
			ArrayList<Operation> operations = new ArrayList<>(selected_account.getOperations());
			model.addAttribute("operations", operations);
		}
		model.addAttribute("typeCompte", typeOperation);
		System.out.println("===== " + codeCompte + " ===== " + typeOperation + " ===== " + montant);
		return "comptes";
	}
	
	
	@RequestMapping(value="/transaction", method=RequestMethod.GET)
	public String transaction() {
		
		return "comptes";
	}

	/*@RequestMapping(value="/consulter-compte/{codeCompte:[0-9a-z]*}", method=RequestMethod.GET)
	public String consulter(Model model, @PathVariable String codeCompte) {
		Compte cp = null;
		try {
			cp = banqueMetier.consulterCompte(codeCompte);
			model.addAttribute("compte", cp);
		} catch (CompteIntrouvableException e) {
			//e.printStackTrace();
			model.addAttribute("exception", e.getMessage());
		}

		return "comptes";
	}*/

}
