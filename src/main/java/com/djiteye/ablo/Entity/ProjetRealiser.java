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
@Table(name="projetRealiser")
public class ProjetRealiser {
	
	@Id
	
	private String id;
	private String titre;
	private String description;
	private LocalDate dateP;
	private String file1name;
	private byte[] file1;
	private String file2name;
	private byte[] file2;
	private String file3name;
	private byte[] file3;
	private String file4name;
	public String getFile1name() {
		return file1name;
	}
	public void setFile1name(String file1name) {
		this.file1name = file1name;
	}
	public String getFile2name() {
		return file2name;
	}
	public void setFile2name(String file2name) {
		this.file2name = file2name;
	}
	public String getFile3name() {
		return file3name;
	}
	public void setFile3name(String file3name) {
		this.file3name = file3name;
	}
	public String getFile4name() {
		return file4name;
	}
	public void setFile4name(String file4name) {
		this.file4name = file4name;
	}

	private byte[] file4;
	
	public ProjetRealiser(String t, String des, LocalDate dp,byte[] f1,byte[] f2,byte[] f3, byte[] f4) {
		super();
		this.titre = t;
		this.description = des;
		this.dateP = dp;
		this.file1= f1;
		this.file2 = f2;
		this.file3 = f3;
		this.file4 = f4;
	}
	public ProjetRealiser() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateP() {
		return dateP;
	}

	public void setDateP(LocalDate dateP) {
		this.dateP = dateP;
	}

	public byte[] getFile1() {
		return file1;
	}

	public void setFile1(byte[] file1) {
		this.file1 = file1;
	}

	public byte[] getFile2() {
		return file2;
	}

	public void setFile2(byte[] file2) {
		this.file2 = file2;
	}

	public byte[] getFile3() {
		return file3;
	}

	public void setFile3(byte[] file3) {
		this.file3 = file3;
	}

	public byte[] getFile4() {
		return file4;
	}

	public void setFile4(byte[] file4) {
		this.file4 = file4;
	}

	
	

}
