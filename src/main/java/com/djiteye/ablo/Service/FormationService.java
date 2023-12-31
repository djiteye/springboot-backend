package com.djiteye.ablo.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiteye.ablo.Entity.Formation;
import com.djiteye.ablo.Repository.FormationRepository;

@Service
public class FormationService {
	
	@Autowired
	public FormationRepository formationR;
	
	public void addf(Formation f){
		 this.formationR.save(f);
	}
	 public Formation getFormation(String id) {
		 Formation other = null;
		return this.formationR.findById(id).orElse(other);
	 }
	 
	 public void deleteFormation(String id) {
		 this.formationR.deleteById(id);}
	 
	 public List<Formation> listF(){
		 List<Formation> list= new ArrayList<>();
		 this.formationR.findAll().forEach(ca -> { 
			 list.add(ca);
			 });
		 return list;
		 
	 }
	 public void upd(String id, Formation form) {
		 this.formationR.save(form);
		 
	 }
	

}
