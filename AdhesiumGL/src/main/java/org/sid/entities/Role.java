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
@Table(name="t_roles")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long idRole;
	private String nomRole;
	private int enabled;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;

	@OneToMany(mappedBy="role")
	private Collection<Privilege> privileges;
	
	public Role(String nomRole, int enabled, Long userCreator, Date dateCreation, Long userUpdator, Date dateUpdate) {
		super();
		this.nomRole = nomRole;
		this.enabled = enabled;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
	}

	public Role() {
		super();
	}


	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	

}
