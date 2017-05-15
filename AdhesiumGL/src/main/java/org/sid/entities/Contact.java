package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_contacts")
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idContact;
	@NotEmpty(message="nom ne doit pas etre null")
	private String nom;
	private String telPortable;
	private String telFixe;
	private String email;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client clientContact;
	public Contact() {
		super();
	}
	
	public Contact(String nom, String telPortable, String telFixe, String email, Long userCreator, Date dateCreation,
			Long userUpdator, Date dateUpdate, Client clientContact) {
		super();
		this.nom = nom;
		this.telPortable = telPortable;
		this.telFixe = telFixe;
		this.email = email;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
		this.clientContact = clientContact;
	}

	public Long getIdContact() {
		return idContact;
	}
	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelPortable() {
		return telPortable;
	}
	public void setTelPortable(String telPortable) {
		this.telPortable = telPortable;
	}
	public String getTelFixe() {
		return telFixe;
	}
	public void setTelFixe(String telFixe) {
		this.telFixe = telFixe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUserCreator() {
		return userCreator;
	}
	public void setUserCreator(Long userCreator) {
		this.userCreator = userCreator;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Long getUserUpdator() {
		return userUpdator;
	}
	public void setUserUpdator(Long userUpdator) {
		this.userUpdator = userUpdator;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	@JsonIgnore
	public Client getClientContact() {
		return clientContact;
	}

	public void setClientContact(Client clientContact) {
		this.clientContact = clientContact;
	}
	
	

}
