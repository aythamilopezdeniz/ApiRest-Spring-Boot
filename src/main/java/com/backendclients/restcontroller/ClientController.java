package com.backendclients.restcontroller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import com.backendclients.model.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.backendclients.businesslayer.service.IClientService;
import com.backendclients.businesslayer.exception.ClientDuplicateException;
import com.backendclients.businesslayer.exception.ClientNotFoundException;

@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private IClientService service;

	@GetMapping("/clients")
	public List<Client> clients() {
		return service.getClients();
	}
	
	@PostMapping("/addClient")
	public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
		if(service.amountClientsEquals(client)>0) throw new ClientDuplicateException("Cliente " + 
				client.getName() + " " + client.getSurname() + " ya se encuentra registrado.");
		service.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@PutMapping("/updateClient/{clientId}")
	public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client, @PathVariable Long clientId) {
		Client obj = service.getClient(clientId);
		if(obj == null) throw new ClientNotFoundException("Cliente " + clientId + " no encontrado.");
		obj.setName(client.getName());
		obj.setSurname(client.getSurname());
		obj.setAge(client.getAge());
		obj.setEmail(client.getEmail());
		service.updateClient(obj);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteClient/{clientId}")
	public ResponseEntity<Long> deleteClient(@PathVariable Long clientId) {
		Client obj = service.getClient(clientId);
		if(obj == null) throw new ClientNotFoundException("Cliente " + clientId + " no encontrado.");
		service.deleteClient(clientId);
		return new ResponseEntity<Long>(clientId, HttpStatus.OK);
	}
}