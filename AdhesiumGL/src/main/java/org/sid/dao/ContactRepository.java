package org.sid.dao;

import java.util.List;

import org.sid.entities.Client;
import org.sid.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	@Query("select c from Contact c where c.nom like :x")
	public Page<Contact> chercher(@Param("x") String mc, Pageable page);
	
	@Modifying
	@Query("delete from Contact c where c.clientContact = ?1")
	public void deleteContactsByClient(Client client);
	
	public List<Contact> findByClientContact(Client client);
	
    
	
}
