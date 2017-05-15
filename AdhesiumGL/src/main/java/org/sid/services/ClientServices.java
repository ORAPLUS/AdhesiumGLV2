package org.sid.services;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.sid.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServices {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<Client> chercher(String mc, String sort, Direction option, int page, int size){
		return clientRepository.chercher("%" + mc + "%", new PageRequest(page, size, option, sort));
	}
	
	@Transactional(readOnly = true)
	private boolean exist(Long idClient) {
		return clientRepository.exists(idClient);
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		if (!exist(id)) {
			throw new ClientNotFoundException("Client Not Found." + id);
		}
		return clientRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Transactional(readOnly = false)
	public Client update(Client client) {
		if (!exist(client.getIdClient())) {
			throw new ClientNotFoundException("Client Not Found." + client.getIdClient());
		}
		return clientRepository.saveAndFlush(client);
	}

	@Transactional(readOnly = false)
	public void delete(Client client) {
		clientRepository.delete(client);
	}
	
}
