
package com.example.demo.entities;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role", schema = "bale")
public class Role {

	@Id
	private String role;
	
	private String description;
	
	@ManyToMany
	@JoinTable(name="roles_privileges", schema = "bale")
	private Collection<Privilege> privileges;

	public Role() {
		super();
	}
	

	public Role(String role, String description, Collection<Privilege> privileges) {
		super();
		this.role = role;
		this.description = description;
		this.privileges = privileges;
	}



	public Role(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
