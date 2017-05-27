package org.sid.metier;

import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMetierImpl implements IClientMetier {
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client addClient(Client c) {
		return clientRepository.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.delete(id);;
	}

	@Override
	public List<Client> listClients() {
		return clientRepository.findAll();
	}

	@Override
	public List<Client> listClientByMC(String mc) {
		return clientRepository.findByKeyword(mc);
	}

}
