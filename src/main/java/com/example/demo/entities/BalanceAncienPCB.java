package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="balance_ancienpcb", schema = "bale")
public class BalanceAncienPCB {
	
	@Id
	private String reference;
	
	private String code;
	
	private String libelle;
	
	private Date date1;
	
	private Date date2;
	
	private Float montant;
	
	private String auxiliare;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ancienpcb_id")
    private AncienPCB ancienpcb;

	public BalanceAncienPCB() {
		super();
	}

	public BalanceAncienPCB(String reference, String code, String libelle, Date date1, Date date2, Float montant,
			String auxiliare, AncienPCB ancienpcb) {
		super();
		this.reference = reference;
		this.code = code;
		this.libelle = libelle;
		this.date1 = date1;
		this.date2 = date2;
		this.montant = montant;
		this.auxiliare = auxiliare;
		this.ancienpcb = ancienpcb;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public String getAuxiliare() {
		return auxiliare;
	}

	public void setAuxiliare(String auxiliare) {
		this.auxiliare = auxiliare;
	}

	public AncienPCB getAncienpcb() {
		return ancienpcb;
	}

	public void setAncienpcb(AncienPCB ancienpcb) {
		this.ancienpcb = ancienpcb;
	}
	
}
