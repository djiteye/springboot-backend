package com.djiteye.ablo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Repository.UserRepository;
//import com.devback.uc.Securite.UserDetailsImpl;
import com.djiteye.ablo.Service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
	private UserRepository userRepos;
	
	@GetMapping("/listU")
	public List<User> getUsers(){
		return userService.getUsers();
		}
	@GetMapping("/U/{id}")
	public User getChambre(@PathVariable String id) {
		return userService.getUser(id);
	}
	@DeleteMapping("/deleteU/{id}")
	public void delete(@PathVariable String id) {
		userService.deleteUser(id);
	}
	@PutMapping("/Up/{id}")
	public void update(@RequestBody User user, @PathVariable String id ) {
		userService.updateUser(user, id);
	}
	 @GetMapping("/me")
	    public ResponseEntity<?> me(@AuthenticationPrincipal User userDetails) {
		 
		  if (userDetails == null) {
	            // L'utilisateur n'est pas authentifié, renvoyer une réponse Unauthorized
	            return ResponseEntity.status(401).build();
	        }

		    
	        return ResponseEntity.ok(userDetails);
	    }

	    @GetMapping("/{id}")
	    //@PreAuthorize("#user.id == #id")
	    public ResponseEntity<?> me(@AuthenticationPrincipal User user, @PathVariable String id) {
	        return ResponseEntity.ok(userRepos.findById(id));
	    }
}
