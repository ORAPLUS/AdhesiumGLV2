package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idClient;
	@NotEmpty(message="nom ne doit pas etre null")
	private String nom;
	private String telPortable;
	private String telFixe;
	private String email;
	private String commentaire;
	private String remarque;
	private String logo;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;
	@OneToMany(mappedBy="client")
	private Collection<Panier> paniers;
	@OneToMany(mappedBy="clientContact")
	private Collection<Contact> contacts;

	
	public Client() {
		super();
	}

	public Client(String nom, String telPortable, String telFixe, String email, String commentaire, String remarque,
			String logo, Long userCreator, Date dateCreation, Long userUpdator, Date dateUpdate) {
		super();
		this.nom = nom;
		this.telPortable = telPortable;
		this.telFixe = telFixe;
		this.email = email;
		this.commentaire = commentaire;
		this.remarque = remarque;
		this.logo = logo;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
	public Collection<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(Collection<Panier> paniers) {
		this.paniers = paniers;
	}
	@JsonIgnore
	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
	

}
