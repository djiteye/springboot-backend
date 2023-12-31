package com.djiteye.ablo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiteye.ablo.Entity.ProjetRealiser;
import com.djiteye.ablo.Repository.ProjetRealiserRepository;

@Service
public class ProjetRealiserService {
	
	@Autowired
	public ProjetRealiserRepository ProjetRealiserR;
	
	public void addf(ProjetRealiser f){
		this.ProjetRealiserR.save(f);
	}
	 public ProjetRealiser getProjet(String id) {
		 ProjetRealiser other = null;
		return this.ProjetRealiserR.findById(id).orElse(other);
	 }
	 
	 public void deleteProjet(String id) {
		 this.ProjetRealiserR.deleteById(id);}
	 
	 public List<ProjetRealiser> listF(){
		 List<ProjetRealiser> list= new ArrayList<>();
		 this.ProjetRealiserR.findAll().forEach(ca -> { 
			 list.add(ca);
			 });
		 return list;
	 }
	 public void upd(String id, ProjetRealiser form) {
		 this.ProjetRealiserR.save(form);
		 
	 }
	
	  public byte[] getImageDataById(String id) {
	    	Optional<ProjetRealiser> other = this.ProjetRealiserR.findById(id);
			
			 if (other.isPresent()) {
		            return other.get().getFile1();
		        } else {
		            throw new RuntimeException("Image not found with ID: " + id);
		        }
			}
	  public byte[] getImageDataById2(String id) {
	    	Optional<ProjetRealiser> other = this.ProjetRealiserR.findById(id);
			
			 if (other.isPresent()) {
		            return other.get().getFile2();
		        } else {
		            throw new RuntimeException("Image not found with ID: " + id);
		        }
			}
	  public byte[] getImageDataById3(String id) {
	    	Optional<ProjetRealiser> other = this.ProjetRealiserR.findById(id);
			
			 if (other.isPresent()) {
		            return other.get().getFile3();
		        } else {
		            throw new RuntimeException("Image not found with ID: " + id);
		        }
			}
	  public byte[] getImageDataById4(String id) {
	    	Optional<ProjetRealiser> other = this.ProjetRealiserR.findById(id);
			
			 if (other.isPresent()) {
		            return other.get().getFile4();
		        } else {
		            throw new RuntimeException("Image not found with ID: " + id);
		        }
			}

}
