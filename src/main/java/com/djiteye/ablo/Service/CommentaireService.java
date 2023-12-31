package com.djiteye.ablo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiteye.ablo.Entity.Commentaire;
import com.djiteye.ablo.Repository.CommentaireRepository;

@Service
public class CommentaireService {
	
	@Autowired
	public CommentaireRepository CommentaireR;
	
	public void addf(Commentaire f){
		this.CommentaireR.save(f);
	}
	 public Commentaire getCommentaire(String id) {
		 Commentaire other = null;
		return this.CommentaireR.findById(id).orElse(other);
	 }
	 
	 public void deleteCommentaire(String id) {
		 this.CommentaireR.deleteById(id);}
	 
	 public List<Commentaire> listF(){
		 List<Commentaire> list= new ArrayList<>();
		 CommentaireR.findAll().forEach(ca -> { 
			 list.add(ca);
			 });
		 return list;
		 
	 }
	 public List<Commentaire> listFP(String proj){
		 List<Commentaire> list= new ArrayList<>();
		 for(Commentaire com: CommentaireR.findAll()) {
			 if(com.getPojetId()==proj) {
				list.add(com);
			 }
		 }
		 return list;	 
	 }
	 public void upd(String id, Commentaire form) {
		 this.CommentaireR.save(form);
		 
	 }

}
