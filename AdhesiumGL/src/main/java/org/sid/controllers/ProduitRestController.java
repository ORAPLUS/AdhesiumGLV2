package org.sid.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.sid.entities.*;
import org.sid.services.ProduitServices;
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
@RequestMapping(value = "/produits")
@Api(name = "Produit Controller", description = "Controlle dédier pour les services de la gestion des produits.", stage = ApiStage.RC)
public class ProduitRestController {
	@Autowired
	private ProduitServices produitServices;
	
	@RequestMapping(value = "/chercherProduits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return une liste des produits filtré par un parametre de recherche")
	public Page<Produit> chercher(@RequestParam(value = "mc", defaultValue = "") @ApiPathParam(name = "mc") String mc,
			@RequestParam(value = "sort", defaultValue = "idProduit") @ApiPathParam(name = "sort") String sort,
			@RequestParam(value = "option", defaultValue = "ASC") @ApiPathParam(name = "option") Direction option,
			@RequestParam(value = "page", defaultValue = "0") @ApiPathParam(name = "page") int page,
			@RequestParam(value = "size", defaultValue = "10") @ApiPathParam(name = "size") int size) {
		return produitServices.chercher(mc, sort, option, page, size);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité return un produit par un ID.")
	public Produit getProduit(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		return produitServices.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet d'inserer un produit.")
	public Produit save(@RequestBody @ApiPathParam(name = "produit") Produit produit) {
		return produitServices.save(produit);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de modifier un produit par son ID.")
	public Produit update(@RequestBody @ApiPathParam(name = "produit") Produit produit,
			@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		produit.setIdProduit(id);
		return produitServices.update(produit);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiMethod(description = "Cette fonctionalité permet de supprimer un produit par son ID.")
	public void delete(@PathVariable("id") @ApiPathParam(name = "id") Long id) {
		Produit produit = produitServices.findById(id);
		produitServices.delete(produit);
	}
}
