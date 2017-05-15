package org.sid.dao;

import org.sid.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("select c from Client c where c.nom like :x")
	public Page<Client> chercher(@Param("x") String mc, Pageable page);
	
}
