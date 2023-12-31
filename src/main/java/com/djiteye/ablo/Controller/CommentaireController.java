package com.djiteye.ablo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djiteye.ablo.Entity.Commentaire;
import com.djiteye.ablo.Service.CommentaireService;

@RestController
@RequestMapping("/commentaire")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentaireController{
	
	@Autowired
	public CommentaireService commentaireS;
	
	@GetMapping("/listC")
	public ResponseEntity<List<Commentaire>> listF(){
		return ResponseEntity.ok(this.commentaireS.listF()); 
	}
	@GetMapping("/listCP/{projetId}")
	public ResponseEntity<List<Commentaire>> listFP(@PathVariable String projetId){
		return ResponseEntity.ok(this.commentaireS.listFP(projetId)); 
	}
	@GetMapping("/C/{id}")
	public ResponseEntity<Commentaire> getP(@PathVariable String id ){
		return ResponseEntity.ok(this.commentaireS.getCommentaire(id));
	}
	@PostMapping("/addC")
	public void addCommentaire(@RequestBody Commentaire form) {
		this.commentaireS.addf(form);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteF(@PathVariable String id) {
		this.commentaireS.deleteCommentaire(id);
	}
	@PutMapping("/upC/{id}")
	public void updateF(@RequestBody Commentaire commentaire, @PathVariable String id) {
		this.commentaireS.upd(id, commentaire);
	}

}
