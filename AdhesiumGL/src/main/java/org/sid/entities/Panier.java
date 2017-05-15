package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_paniers")
public class Panier implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idPanier;
	private int qt;
	private String modules;
	private String type;
	private String install;
	private String os;
	private String target;
	private String commentaire;
	private Date dateAchat;
	private Date dateActivation;
	private Date dateExpiration;
	private Date dateStop;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	@ManyToOne
	@JoinColumn(name="idProduit")
	private Produit produit;
	
	
	public Panier() {
		super();
	}
	
	public Panier(int qt, String modules, String type, String install, String os, String target, String commentaire,
			Date dateAchat, Date dateActivation, Date dateExpiration, Date dateStop, Long userCreator,
			Date dateCreation, Long userUpdator, Date dateUpdate, Client client, Produit produit) {
		super();
		this.qt = qt;
		this.modules = modules;
		this.type = type;
		this.install = install;
		this.os = os;
		this.target = target;
		this.commentaire = commentaire;
		this.dateAchat = dateAchat;
		this.dateActivation = dateActivation;
		this.dateExpiration = dateExpiration;
		this.dateStop = dateStop;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
		this.client = client;
		this.produit = produit;
	}

	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInstall() {
		return install;
	}
	public void setInstall(String install) {
		this.install = install;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	public Long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Date getDateActivation() {
		return dateActivation;
	}

	public void setDateActivation(Date dateActivation) {
		this.dateActivation = dateActivation;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public Date getDateStop() {
		return dateStop;
	}

	public void setDateStop(Date dateStop) {
		this.dateStop = dateStop;
	}
	@JsonIgnore
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@JsonIgnore
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
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
	
	

}
