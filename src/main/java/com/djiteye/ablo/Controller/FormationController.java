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

import com.djiteye.ablo.Entity.Formation;
import com.djiteye.ablo.Service.FormationService;

@RestController
@RequestMapping("/formation")
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {
	
	@Autowired
	private FormationService foms;
	
	@GetMapping("/listF")
	public ResponseEntity<List<Formation>> listF(){
		return ResponseEntity.ok(this.foms.listF()); 
	}
	@GetMapping("/F/{id}")
	public ResponseEntity<Formation> getF(@PathVariable String id ){
		return ResponseEntity.ok(this.foms.getFormation(id));
	}
	@PostMapping("/addF")
	public void addformation(@RequestBody Formation form) {
		this.foms.addf(form);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteF(@PathVariable String id) {
		this.foms.deleteFormation(id);
	}
	@PutMapping("/upF/{id}")
	public void updateF(@RequestBody Formation formation, @PathVariable String id) {
		this.foms.upd(id, formation);
	}
	
	

}
