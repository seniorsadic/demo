package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operation", schema = "bale")
public class Operation {
	
	@Id
	private String libelle;
	
	private String codeints;
	
	private String codeBanque;
	
	

	public Operation() {
		super();
	}

	public Operation(String libelle, String codeints, String codeBanque) {
		super();
		this.libelle = libelle;
		this.codeints = codeints;
		this.codeBanque = codeBanque;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodeints() {
		return codeints;
	}

	public void setCodeints(String codeints) {
		this.codeints = codeints;
	}

	public String getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}
	
	

}
