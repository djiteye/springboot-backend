package com.djiteye.ablo.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.djiteye.ablo.Entity.ERole;
import com.djiteye.ablo.Entity.RefreshToken;
import com.djiteye.ablo.Entity.Role;
import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Repository.RoleRepos;
import com.djiteye.ablo.Repository.UserRepository;
import com.djiteye.ablo.Securite.UserServiceDetailsImpl;
import com.djiteye.ablo.Securite.Jwt.JwtUtils;
import com.djiteye.ablo.Service.RoleService;
import com.djiteye.ablo.Service.UserService;

//import com.devback.uc.Repository.RefreshTokenRepository;


@Component
public class StartupRunner{

	    @Autowired
		UserRepository userRepository;

		@Autowired
		RoleRepos roleRepository;

		@Autowired
		PasswordEncoder encoder;
		
		@Autowired
		UserService userService;
		
		@Autowired
		UserServiceDetailsImpl userDetailsServiceImpl;
		
		/*@Autowired
		private RefreshTokenRepository refreshTokenRepository;*/
		
		@Autowired
	    private RoleService roleService;
		

		@Autowired
		private JwtUtils jwtUtils;
		
		
		
	 @EventListener(ContextRefreshedEvent.class)
	    public void userdefault() {
		

			
			long idd= this.userService.getUsers().size();
			if(idd==0) {
				User user = new User(//signUpRequest.getId(),
			             //Id,
			             "Abdoulaye", 
						 "Abdoulaye@gmail.com",
						 encoder.encode("Ablo2023@"),
						 "genre"
						 );

		Role ROLE_ADMIN= new Role(ERole.ROLE_ADMIN);
		Role ROLE_USER= new Role(ERole.ROLE_USER);
		if(this.roleService.lista().size()== 0) {
		this.roleService.createRole(ROLE_ADMIN);}
		if(this.roleService.listu().size()== 0) {
		this.roleService.createRole(ROLE_USER);}
		//Set<String> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(adminRole );

		user.setRoles(roles);
		userRepository.save(user);
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setOwner(user);
		//refreshTokenRepository.save(refreshToken);

		String accessToken = jwtUtils.generateAccessToken(user);
		String refreshTokenString = jwtUtils.generateRefreshToken(user, refreshToken.getId());

		//return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
		System.out.println("l'utilisateur par defaut a été créer");
				
			}else {
				System.out.println("l'utilisateur par defaut existe déjà");
			}
	    }
}
