package com.djiteye.ablo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name="commentaire")
public class Commentaire {

	@Id
	
	private String id;
	private String PojetId;
	private String userId;
	private String corps;
	
	public String getPojetId() {
		return PojetId;
	}

	public void setPojetId(String pojetId) {
		PojetId = pojetId;
	}

	public Commentaire(String projetId, String userId, String corps) {
		super();
		this.PojetId=projetId;
		this.userId= userId;
		this.corps= corps;
	}

	public Commentaire() {
		
	}
	public String getId() {
		return PojetId;
	}

	public void setId(String id) {
		PojetId = id;
	}

	public String getCommentaireId() {
		return id;
	}

	public void setCommentaireId(String commentaireId) {
		this.id = commentaireId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}
	
}
