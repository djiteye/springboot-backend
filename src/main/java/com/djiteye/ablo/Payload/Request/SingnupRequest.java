package com.djiteye.ablo.Payload.Request;

import java.util.Set;

import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
public class SingnupRequest {
	private String username;
	 //@Email
	private String email;
	    
	 public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getGenre() {
		return genre;
	}




	public void setGenre(String genre) {
		this.genre = genre;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Set<String> getRoles() {
		return roles;
	}




	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}




	private String genre;
	    
	private String password;
	    
	private Set<String> roles;
	    
	    
	    
	    
	    public SingnupRequest( String a, String e, String g , String p, Set<String> r) {
	    	//this.id=id;
	    	this.username=a;
	    	this.email=e;
	    	this.genre=g;
	    	this.password=p;
	    	this.roles=r;
	    	
	    }

}
