package com.backendclients.businesslayer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.backendclients.model.entity.Client;
import com.backendclients.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClientService implements IClientService {
	
	@Autowired
	private ClientRepository repository;

	@Override
	public List<Client> getClients() {
		return repository.findAll();
	}

	@Override
	public Client getClient(Long clientId) {
		Client obj = repository.findById(clientId).get();
		if(obj.equals(null)) return null;
		else return obj;
	}
	
	@Override
	public int amountClientsEquals(Client client) {
		int amountClients = 0;
		for (Client obj : repository.findAll()) {
			if(obj.getName().equals(client.getName()) &&
					obj.getSurname().equals(client.getSurname())) amountClients++;
		}
		return amountClients;
	}

	@Override
	public void saveClient(Client client) {
		repository.save(client);
	}

	@Override
	public void updateClient(Client client) {
//		Client obj = getClient(client.getId());
//		obj.setName(client.getName());
//		obj.setSurname(client.getSurname());
//		repository.save(obj);
		repository.save(client);
	}

	@Override
	public void deleteClient(Long clientId) {
		repository.deleteById(clientId);
	}
}