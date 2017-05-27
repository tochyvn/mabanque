package org.sid.metier;

import java.util.List;

import org.sid.entities.Client;

public interface IClientMetier {
	
	public Client addClient(Client c);
	public void deleteClient(Long id);
	public List<Client> listClients();
	public List<Client> listClientByMC(String mc);
	
}
