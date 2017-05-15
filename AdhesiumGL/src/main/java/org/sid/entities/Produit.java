package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_produits")
public class Produit implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idProduit;
	private String nom;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;
	@OneToMany(mappedBy="produit")
	private Collection<Panier> paniers;

	public Produit() {
		super();
	}

	public Produit(String nom, Long userCreator, Date dateCreation, Long userUpdator, Date dateUpdate) {
		super();
		this.nom = nom;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
	}


	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
	
	

}
