package com.example.demo.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ancien_pcb", schema = "bale")
public class AncienPCB {
	
	@Id
	private String compte_ancien;
	
	private String intitule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private AncienPCB parent;
	
	private Boolean splite;
	
	public AncienPCB() {
		super();
	}
	
	public AncienPCB(String compte_ancien, String intitule, AncienPCB parent, Boolean splite) {
		super();
		this.compte_ancien = compte_ancien;
		this.intitule = intitule;
		this.parent = parent;
		this.splite = splite;
	}

	public String getCompte_ancien() {
		return compte_ancien;
	}

	public void setCompte_ancien(String compte_ancien) {
		this.compte_ancien = compte_ancien;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public AncienPCB getParent() {
		return parent;
	}

	public void setParent(AncienPCB parent) {
		this.parent = parent;
	}

	public Boolean getSplite() {
		return splite;
	}

	public void setSplite(Boolean splite) {
		this.splite = splite;
	}
	
}
