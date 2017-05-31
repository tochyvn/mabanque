package org.sid.web;

import java.util.ArrayList;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Exception.CompteIntrouvableException;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	
	@RequestMapping(value="operations")
	public String operation() {
		return "comptes";
	}
	
	@RequestMapping("/comptes")
	public String compte() {
		return "comptes";
	}
	
	@RequestMapping("clients")
	public String client() {
		return "clients";
	}
	
	@RequestMapping(value="/consulter-compte")
	public String consulter(Model model, String codeCompte) {
		Compte cp = null;
		System.out.println("CODE COMPTE " + codeCompte);
		if (codeCompte != null) {
			try {
				cp = banqueMetier.consulterCompte(codeCompte);
				ArrayList<Operation> operations = new ArrayList<>(cp.getOperations());
				model.addAttribute("compte", cp);
				model.addAttribute("operations", operations);
			} catch (CompteIntrouvableException e) {
				//e.printStackTrace();
				model.addAttribute("exception", e.getMessage());
			}
		} else {
			model.addAttribute("exception", "");
		}
		
		return "comptes";
	}
	
	@RequestMapping(value="transaction", method=RequestMethod.POST)
	public String transaction(Model model) {
		
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
