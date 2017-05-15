package org.sid.services;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.sid.exception.ProduitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProduitServices {

	@Autowired
	private ProduitRepository produitRepository;

	@Transactional(readOnly = true)
	public Page<Produit> chercher(String mc, String sort, Direction option, int page, int size){
		return produitRepository.chercher("%" + mc + "%", new PageRequest(page, size, option, sort));
	}
	
	@Transactional(readOnly = true)
	private boolean exist(Long idProduit) {
		return produitRepository.exists(idProduit);
	}

	@Transactional(readOnly = true)
	public Produit findById(Long id) {
		if (!exist(id)) {
			throw new ProduitNotFoundException("Produit Not Found." + id);
		}
		return produitRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Produit save(Produit produit) {
		return produitRepository.save(produit);
	}

	@Transactional(readOnly = false)
	public Produit update(Produit produit) {
		if (!exist(produit.getIdProduit())) {
			throw new ProduitNotFoundException("Produit Not Found." + produit.getIdProduit());
		}
		return produitRepository.saveAndFlush(produit);
	}

	@Transactional(readOnly = false)
	public void delete(Produit produit) {
		produitRepository.delete(produit);
	}
	
}
