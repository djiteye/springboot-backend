package com.djiteye.ablo.Securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Repository.UserRepository;

@Service
public class UserServiceDetailsImpl implements UserDetailsService{
	@Autowired
	private UserRepository userR;
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userR.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("username not found"));
		//return UserDetailsImpl.build(user);
	}
	
	public User findById(String id) {
		return userR.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("user id not found"));
	}

}
