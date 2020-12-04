package com.backendclients.businesslayer.service;

import java.util.List;
import com.backendclients.model.entity.Client;

public interface IClientService {
	public List<Client> getClients();
	public Client getClient(Long clientId);
	public int amountClientsEquals(Client client);
	public void saveClient(Client client);
	public void updateClient(Client client);
	public void deleteClient(Long clientId);
}