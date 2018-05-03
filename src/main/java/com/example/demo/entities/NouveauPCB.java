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
	private String compte_nouveau;
	private String intitule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private NouveauPCB parent;
	
	public NouveauPCB() {
		super();
	}

	public NouveauPCB(String compte_ancien, String intitule, NouveauPCB parent) {
		super();
		this.compte_nouveau = compte_ancien;
		this.intitule = intitule;
		this.parent = parent;
	}

	public String getCompte_nouveau() {
		return compte_nouveau;
	}

	public void setCompte_nouveau(String compte_nouveau) {
		this.compte_nouveau = compte_nouveau;
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
