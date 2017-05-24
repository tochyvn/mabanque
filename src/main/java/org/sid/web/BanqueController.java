package org.sid.web;

import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanqueController {
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping("/")
	public String index() {
		return "comptes";
	}
	
	@RequestMapping("/operations")
	public String operation() {
		return "comptes";
	}
	
	@RequestMapping("clients")
	public String client() {
		return "clients";
	}

}
