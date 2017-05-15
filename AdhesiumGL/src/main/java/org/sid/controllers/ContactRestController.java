package org.sid.controllers;

import java.util.List;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.sid.entities.*;
import org.sid.services.ClientServices;
import org.sid.services.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contacts")
@Api(name = "Contact Controller", description = "Controlle dédier pour les services de la gestion des contacts.", stage = ApiStage.RC)
public class ContactRestController {
	@Autowired
	private ContactServices contactServices;
	@Autowired
	private ClientServices clientServices;
	
	@RequestMapping(value = "/chercherContacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return une liste des contacts filtré par un parametre de recherche")
	public Page<Contact> chercher(@RequestParam(value = "mc", defaultValue = "") @ApiPathParam(name = "mc") String mc,
			@RequestParam(value = "sort", defaultValue = "idContact") @ApiPathParam(name = "sort") String sort,
			@RequestParam(value = "option", defaultValue = "ASC") @ApiPathParam(name = "option") Direction option,
			@RequestParam(value = "page", defaultValue = "0") @ApiPathParam(name = "page") int page,
			@RequestParam(value = "size", defaultValue = "10") @ApiPathParam(name = "size") int size) {
		return contactServices.chercher(mc, sort, option, page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return un contact par un ID.")
	public Contact getContact(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		return contactServices.findById(id);
	}
	
	@RequestMapping(value = "/listContacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return un contact par un ID.")
	public List<Contact> getContactsByClient(@RequestParam(value = "idClient", defaultValue = "") @ApiPathParam(name = "idClient") Long idClient) {
		Client client = clientServices.findById(idClient);
		return contactServices.contactsByClient(client);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet d'inserer un contact.")
	public Contact save(@RequestBody @ApiPathParam(name = "contact") Contact contact) {
		return contactServices.save(contact);
	}
	
	@RequestMapping(value = "/{idClient}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet d'inserer une liste des contacts.")
	public void saveAll(@RequestBody @ApiPathParam(name = "contacts") List<Contact> contacts,
						@PathVariable("idClient") @ApiPathParam(name = "idClient") Long idClient) {
		Client client = clientServices.findById(idClient);
		for(Contact c : contacts) {
			c.setClientContact(client);
			contactServices.save(c);
	    }
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de modifier un contact par son ID.")
	public Contact update(@RequestBody @ApiPathParam(name = "contact") Contact contact,
			@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		contact.setIdContact(id);
		return contactServices.update(contact);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de supprimer un contact par son ID.")
	public void delete(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		Contact contact = contactServices.findById(id);
		contactServices.delete(contact);
	}
	
	@RequestMapping(value = "/delContacts/{idClient}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité delete les contacts par un ID.")
	public void deleteContactsByClient(@PathVariable("idClient") @ApiPathParam(name = "idClient") Long idClient) {
		Client client = clientServices.findById(idClient);
	    contactServices.deleteContactsByClient(client);
	}
	
}
