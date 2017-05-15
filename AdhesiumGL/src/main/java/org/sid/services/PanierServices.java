package org.sid.services;

import org.sid.dao.PanierRepository;
import org.sid.entities.Panier;
import org.sid.exception.PanierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PanierServices {

	@Autowired
	private PanierRepository panierRepository;

	@Transactional(readOnly = true)
	public Page<Panier> chercher(String mc, String sort, Direction option, int page, int size){
		return panierRepository.chercher("%" + mc + "%", new PageRequest(page, size, option, sort));
	}
	
	@Transactional(readOnly = true)
	private boolean exist(Long idPanier) {
		return panierRepository.exists(idPanier);
	}

	@Transactional(readOnly = true)
	public Panier findById(Long id) {
		if (!exist(id)) {
			throw new PanierNotFoundException("Panier Not Found." + id);
		}
		return panierRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Panier save(Panier panier) {
		return panierRepository.save(panier);
	}

	@Transactional(readOnly = false)
	public Panier update(Panier panier) {
		if (!exist(panier.getIdPanier())) {
			throw new PanierNotFoundException("Panier Not Found." + panier.getIdPanier());
		}
		return panierRepository.saveAndFlush(panier);
	}

	@Transactional(readOnly = false)
	public void delete(Panier panier) {
		panierRepository.delete(panier);
	}
	
}
