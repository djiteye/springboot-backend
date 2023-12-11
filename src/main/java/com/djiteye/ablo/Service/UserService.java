package com.djiteye.ablo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userR;
	public List<User> getUsers(){
		 List<User> car= new ArrayList<>();
		 userR.findAll().forEach(ca -> { 
			 car.add(ca);
			 });
		 return car;
	 }
	 public User getUser(String id) {
		 User other = null;
		return userR.findById(id).orElse(other);
	 }
	 
	 public void deleteUser(String id) {
		 userR.deleteById(id);
	 }

}
