package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier metier;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		/*ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
		clientRepository.save(new Client("TOCHAP", "tochlionimy@gmail.com"));*/
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("TOCHAP", "tochlionimy@gmail.com"));
		Client c2 = clientRepository.save(new Client("FAIDATI", "fadaitihafhi@gmail.com"));
		
		Compte cp1 = compteRepository.save(
				new CompteCourant("c1", new Date(), 90000, c1, 6000)
				);
		Compte cp2 = compteRepository.save(
				new CompteEpargne("c2", new Date(), 6000, c2)
				);
		
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Versement(new Date(), 400, cp2));
		operationRepository.save(new Versement(new Date(), 2300, cp2));
		operationRepository.save(new Versement(new Date(), 3000, cp2));
		
		metier.verser(cp1.getCodeCompte(), 1000);
		metier.verser("c2", 1000);
		metier.retirer("c2", 500);
		
	}
	
}
