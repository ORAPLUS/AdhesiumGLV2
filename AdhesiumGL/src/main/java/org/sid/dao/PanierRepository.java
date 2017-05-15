package org.sid.dao;

import org.sid.entities.Panier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
	@Query("select p from Panier p where p.commentaire like :x")
	public Page<Panier> chercher(@Param("x") String mc, Pageable page);
}
