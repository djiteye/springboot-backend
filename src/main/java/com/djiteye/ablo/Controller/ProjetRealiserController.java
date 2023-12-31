package com.djiteye.ablo.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.djiteye.ablo.Entity.ProjetRealiser;
import com.djiteye.ablo.Service.ProjetRealiserService;

@RestController
@RequestMapping("/projetrealiser")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetRealiserController {
	@Autowired
	public ProjetRealiserService projetS;
	
	@GetMapping("/listP")
	public List<ProjetRealiser> listF(){
		return this.projetS.listF(); 
	}
	@GetMapping("/P/{id}")
	public ResponseEntity<ProjetRealiser> getF(@PathVariable String id ){
		return ResponseEntity.ok(this.projetS.getProjet(id));
	}
	@PostMapping("/addP")
	public void addformation(@RequestPart("file1") MultipartFile image1,@RequestPart("file2") MultipartFile image2,@RequestPart("file3") MultipartFile image3,@RequestPart("file4") MultipartFile image4,@RequestPart("titre") String titre,@RequestPart("description") String description) throws IOException {
		ProjetRealiser form= new ProjetRealiser();
		String name1=image1.getOriginalFilename();
		byte[] file1= image1.getBytes();
		String name2=image2.getOriginalFilename();
		byte[] file2= image2.getBytes();
		String name3=image3.getOriginalFilename();
		byte[] file3= image3.getBytes();
		String name4=image4.getOriginalFilename();
		byte[] file4= image4.getBytes();
		

		form.setTitre(titre);
		form.setDescription(description);
		
		ZoneId zoneId = ZoneId.systemDefault();
        // Obtient la date actuelle dans la zone horaire locale
        LocalDate dat = LocalDate.now(zoneId);
	
		form.setDateP(dat);
		form.setFile1name(name1);
		form.setFile1(file1);
		form.setFile2name(name2);
		form.setFile2(file2);
		form.setFile3name(name3);
		form.setFile3(file3);
		form.setFile4name(name4);
		form.setFile4(file4);
		this.projetS.addf(form);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteP(@PathVariable String id) {
		this.projetS.deleteProjet(id);
	}
	@PutMapping("/upP/{id}")
	public void updateP(@RequestBody ProjetRealiser projetRealiser, @PathVariable String id) {
		this.projetS.upd(id, projetRealiser);
	}

	 @GetMapping("image1/{id}")
	    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
	        try {
	            // Récupérer les données binaires de l'image depuis la base de données
	            byte[] imageData = projetS.getImageDataById(id);

	            // Retourner les données binaires en tant qu'image JPEG
	            return ResponseEntity.ok().body(imageData);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(null);
	        }
	    }
	    @GetMapping("image2/{id}")
	    public ResponseEntity<byte[]> getImage2(@PathVariable String id) {
	        try {
	            // Récupérer les données binaires de l'image depuis la base de données
	            byte[] imageData = projetS.getImageDataById2(id);

	            // Retourner les données binaires en tant qu'image JPEG
	            return ResponseEntity.ok().body(imageData);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(null);
	        }
	    }
	    @GetMapping("image3/{id}")
	    public ResponseEntity<byte[]> getImage3(@PathVariable String id) {
	        try {
	            // Récupérer les données binaires de l'image depuis la base de données
	            byte[] imageData = projetS.getImageDataById3(id);

	            // Retourner les données binaires en tant qu'image JPEG
	            return ResponseEntity.ok().body(imageData);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(null);
	        }
	    }
	    @GetMapping("image4/{id}")
	    public ResponseEntity<byte[]> getImage4(@PathVariable String id) {
	        try {
	            // Récupérer les données binaires de l'image depuis la base de données
	            byte[] imageData = projetS.getImageDataById4(id);

	            // Retourner les données binaires en tant qu'image JPEG
	            return ResponseEntity.ok().body(imageData);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(null);
	        }
	    }
}
