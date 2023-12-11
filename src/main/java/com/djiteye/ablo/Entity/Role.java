package com.djiteye.ablo.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Document(collection = "roles")
public class Role {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private String id;
	
	public ERole name;
	  
	  public Role(ERole name) {
		  //super();
		    this.name = name;
		  }

	  public Role() {
		// TODO Auto-generated constructor stub
	}
	  public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public ERole getName() {
			return name;
		}

		public void setName(ERole name) {
			this.name = name;
		}
	

}
