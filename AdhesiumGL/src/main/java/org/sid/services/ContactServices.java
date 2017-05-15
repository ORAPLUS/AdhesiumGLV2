package org.sid.services;

import java.util.List;

import org.sid.dao.ContactRepository;
import org.sid.entities.Client;
import org.sid.entities.Contact;
import org.sid.exception.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactServices {

	@Autowired
	private ContactRepository ContactRepository;

	@Transactional(readOnly = true)
	public Page<Contact> chercher(String mc, String sort, Direction option, int page, int size){
		return ContactRepository.chercher("%" + mc + "%", new PageRequest(page, size, option, sort));
	}
	
	@Transactional(readOnly = true)
	private boolean exist(Long idContact) {
		return ContactRepository.exists(idContact);
	}

	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		if (!exist(id)) {
			throw new ContactNotFoundException("Contact Not Found." + id);
		}
		return ContactRepository.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<Contact> contactsByClient(Client client){
		return ContactRepository.findByClientContact(client);
	}
	
	@Transactional(readOnly = false)
	public void deleteContactsByClient(Client client){
		ContactRepository.deleteContactsByClient(client);
	}

	@Transactional(readOnly = false)
	public Contact save(Contact Contact) {
		return ContactRepository.save(Contact);
	}

	@Transactional(readOnly = false)
	public Contact update(Contact Contact) {
		if (!exist(Contact.getIdContact())) {
			throw new ContactNotFoundException("Contact Not Found." + Contact.getIdContact());
		}
		return ContactRepository.saveAndFlush(Contact);
	}

	@Transactional(readOnly = false)
	public void delete(Contact Contact) {
		ContactRepository.delete(Contact);
	}
	
}
