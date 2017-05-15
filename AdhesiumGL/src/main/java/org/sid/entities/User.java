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
@Table(name = "t_users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idUser;
	private String username;
	private String password;
	private String email;
	private int userEnabled;
	private Long userCreator;
	private Date dateCreation;
	private Long userUpdator;
	private Date dateUpdate;

	@OneToMany(mappedBy = "user")
	private Collection<Privilege> privileges;

	public User(String username, String password,String email, int userEnabled, Long userCreator, Date dateCreation,
			Long userUpdator, Date dateUpdate) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.userEnabled = userEnabled;
		this.userCreator = userCreator;
		this.dateCreation = dateCreation;
		this.userUpdator = userUpdator;
		this.dateUpdate = dateUpdate;
	}

	public User() {
		super();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(int userEnabled) {
		this.userEnabled = userEnabled;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
