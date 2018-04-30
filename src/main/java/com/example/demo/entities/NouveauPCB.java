package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="nouveau_pcb", schema = "bale")
public class NouveauPCB {
	@Id
	private String compte_ancien;
	private String intitule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private NouveauPCB parent;
	
	public NouveauPCB() {
		super();
	}

	public NouveauPCB(String compte_ancien, String intitule, NouveauPCB parent) {
		super();
		this.compte_ancien = compte_ancien;
		this.intitule = intitule;
		this.parent = parent;
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

	public NouveauPCB getParent() {
		return parent;
	}

	public void setParent(NouveauPCB parent) {
		this.parent = parent;
	}
}
