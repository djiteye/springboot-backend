package com.djiteye.ablo.Entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name="formation")
public class Formation {
	@Id
	private String id;
	private String nom;
	private String ecole;
	private String ville;
	private LocalDate Datedebut;
	private LocalDate Datefin;
	
	public Formation(String n, String e, String v, LocalDate dt, LocalDate df) {
		super();
		this.nom= n;
		this.ecole= e;
		this.ville = v;
		this.Datedebut= dt;
		this.Datefin= df;
	}
	public Formation() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public LocalDate getDatedebut() {
		return Datedebut;
	}

	public void setDatedebut(LocalDate datedebut) {
		Datedebut = datedebut;
	}

	public LocalDate getDatefin() {
		return Datefin;
	}

	public void setDatefin(LocalDate datefin) {
		Datefin = datefin;
	}
	

}
