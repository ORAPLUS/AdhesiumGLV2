package org.sid.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.sid.entities.*;
import org.sid.services.PanierServices;
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
@RequestMapping(value = "/paniers")
@Api(name = "Panier Controller", description = "Controlle dédier pour les services de la gestion des paniers.", stage = ApiStage.RC)
public class PanierRestController {
	@Autowired
	private PanierServices panierServices;
	
	@RequestMapping(value = "/chercherPaniers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return une liste des paniers filtré par un parametre de recherche")
	public Page<Panier> chercher(@RequestParam(value = "mc", defaultValue = "") @ApiPathParam(name = "mc") String mc,
			@RequestParam(value = "sort", defaultValue = "idPanier") @ApiPathParam(name = "sort") String sort,
			@RequestParam(value = "option", defaultValue = "ASC") @ApiPathParam(name = "option") Direction option,
			@RequestParam(value = "page", defaultValue = "0") @ApiPathParam(name = "page") int page,
			@RequestParam(value = "size", defaultValue = "10") @ApiPathParam(name = "size") int size) {
		return panierServices.chercher(mc, sort, option, page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return un panier par un ID.")
	public Panier getPanier(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		return panierServices.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet d'inserer un panier.")
	public Panier save(@RequestBody @ApiPathParam(name = "panier") Panier panier) {
		return panierServices.save(panier);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de modifier un panier par son ID.")
	public Panier update(@RequestBody @ApiPathParam(name = "panier") Panier panier,
			@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		panier.setIdPanier(id);
		return panierServices.update(panier);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de supprimer un panier par son ID.")
	public void delete(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		Panier panier = panierServices.findById(id);
		panierServices.delete(panier);
	}
}
