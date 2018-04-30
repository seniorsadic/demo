package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users", schema = "bale")
public class Users {
	@Id
	private String username;
	private String password;
	private String email;
	private String nom;
	private boolean actived;
	@ManyToMany
	@JoinTable(name="users_roles", schema = "bale")
	private Collection<Role> roles;
	
	public Users() {
		super();
	}
	public Users(String username, String password, String email, String nom, boolean actived) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nom = nom;
		this.actived=actived;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	
	

}
