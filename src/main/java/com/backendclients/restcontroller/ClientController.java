package com.backendclients.restcontroller;

import java.util.List;
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
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private IClientService service;
	private static final Logger LOG = Logger.getLogger("com.backendclients.restcontroller.ClientController");

	@GetMapping("/clients")
	public List<Client> clients() {
		return service.getClients();
	}
	
	@PostMapping("/addClient")
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		LOG.warning("Cliente repetido " + service.amountClientsEquals(client));
		if(service.amountClientsEquals(client)>0) return new ResponseEntity<Client>(client, HttpStatus.CONFLICT);
		service.saveClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@PutMapping("/updateClient/{clientId}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Long clientId) {
		try { 
			Client obj = service.getClient(clientId);
			obj.setName(client.getName());
			obj.setSurname(client.getSurname());
			service.updateClient(obj);
		} catch (Exception e) { return new ResponseEntity<Client>(client, HttpStatus.NOT_FOUND); }
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteClient/{clientId}")
	public ResponseEntity<Long> deleteClient(@PathVariable Long clientId) {
		try { 
			Client obj = service.getClient(clientId);
			LOG.warning("Cliente que desea eliminar " + obj.getId());
		} catch (Exception e) { return new ResponseEntity<Long>(clientId, HttpStatus.NOT_FOUND); }
		service.deleteClient(clientId);
		return new ResponseEntity<Long>(clientId, HttpStatus.OK);
	}
}
