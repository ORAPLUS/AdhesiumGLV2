package org.sid.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.sid.entities.*;
import org.sid.services.ClientServices;
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
@RequestMapping(value = "/clients")
@Api(name = "Client Controller", description = "Controlle dédier pour les services de la gestion des clients.", stage = ApiStage.RC)
public class ClientRestController {
	@Autowired
	private ClientServices clientServices;
	
	@RequestMapping(value = "/chercherClients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return une liste des clients filtré par un parametre de recherche")
	public Page<Client> chercher(@RequestParam(value = "mc", defaultValue = "") @ApiPathParam(name = "mc") String mc,
			@RequestParam(value = "sort", defaultValue = "idClient") @ApiPathParam(name = "sort") String sort,
			@RequestParam(value = "option", defaultValue = "ASC") @ApiPathParam(name = "option") Direction option,
			@RequestParam(value = "page", defaultValue = "0") @ApiPathParam(name = "page") int page,
			@RequestParam(value = "size", defaultValue = "10") @ApiPathParam(name = "size") int size) {
		return clientServices.chercher(mc, sort, option, page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return un client par un ID.")
	public Client getClient(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		return clientServices.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet d'inserer un client.")
	public Client save(@RequestBody @ApiPathParam(name = "client") Client client) {
		return clientServices.save(client);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de modifier un client par son ID.")
	public Client update(@RequestBody @ApiPathParam(name = "client") Client client,
			@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		client.setIdClient(id);
		return clientServices.update(client);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de supprimer un client par son ID.")
	public void delete(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		Client client = clientServices.findById(id);
		clientServices.delete(client);
	}
}
