package com.djiteye.ablo.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.djiteye.ablo.Entity.ERole;
import com.djiteye.ablo.Entity.Role;
import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Repository.RoleRepos;
import com.djiteye.ablo.Repository.UserRepository;

@Service
public class RoleService {
	@Autowired
    private RoleRepos roleRepository;
	
	@Autowired
    private UserRepository userRepository;

    
    public RoleService(RoleRepos roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
    
    public List<Role> lista(){
    	List<Role> a= new ArrayList<>();
    	for(Role rl: roleRepository.findAll()) {
    		if(rl.getName().toString().equals("ROLE_ADMIN")) {
    			a.add(rl);
    		}
    	}
		return a;
    	
    }
    public List<Role> listm(){
    	List<Role> a= new ArrayList<>();
    	for(Role rl: roleRepository.findAll()) {
    		if(rl.getName().toString().equals("ROLE_MODERATOR")) {
    			a.add(rl);
    		}
    	}
		return a;
    	
    }
    public List<Role> listu(){
    	List<Role> a= new ArrayList<>();
    	for(Role rl: roleRepository.findAll()) {
    		if(rl.getName().toString().equals("ROLE_USER")) {
    			a.add(rl);
    		}
    	}
		return a;
    	
    }
    public String getRole(Long id) {
		//User other = null;
		Role rd=new Role();
		User a= userRepository.findById(id);
		Set<Role> rl= a.getRole();
		/*rl.forEach(ca -> { 
			 r = ca;
			 });*/
		for(Role r: rl) {
			rd=r;
		}
		return rd.getName().name();
		
	}
}

