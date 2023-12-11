package com.djiteye.ablo.Controller;
import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
import java.util.Set;
//import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.djiteye.ablo.Entity.ERole;
import com.djiteye.ablo.Entity.RefreshToken;
import com.djiteye.ablo.Entity.Role;
import com.djiteye.ablo.Entity.User;
import com.djiteye.ablo.Payload.Request.LoginRequest;
import com.djiteye.ablo.Payload.Request.SingnupRequest;
import com.djiteye.ablo.Payload.Request.TokenDTO;
//import com.devback.uc.Payload.Response.JwtResponse;
import com.djiteye.ablo.Payload.Response.MessageResponse;
import com.djiteye.ablo.Repository.RefreshTokenRepository;
import com.djiteye.ablo.Repository.RoleRepos;
import com.djiteye.ablo.Repository.UserRepository;
import com.djiteye.ablo.Securite.UserServiceDetailsImpl;
import com.djiteye.ablo.Securite.Jwt.JwtUtils;
import com.djiteye.ablo.Service.RoleService;
import com.djiteye.ablo.Service.UserService;





@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

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
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
    private RoleService roleService;
	

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		/*System.out.println("les informations d'authentification sont: "+ loginRequest.getUsername()+" "+ loginRequest.getPassword());
		Optional<User> princip= userRepository.findByUsername(loginRequest.getUsername());
		LoginRequest pr= new LoginRequest(princip.get().getUsername(), princip.get().getPassword());
		LoginRequest cr= new LoginRequest(loginRequest.getUsername(), loginRequest.getPassword());
		System.out.println("les informations d'authentification du principal sont: "+ pr.getUsername()+" "+ pr.getPassword());
		System.out.println("les informations d'authentification du crential sont: "+ cr.getUsername()+" "+ cr.getPassword());*/
		
		//if(encoder.matches(cr.getPassword(),pr.getPassword())/*pr.getPassword().equals(cr.getPassword())*/) {
		/*	return ResponseEntity.ok(princip);
		}
		else{
			return (ResponseEntity<?>) ResponseEntity.internalServerError();}*/
	 
	  /*  Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(pr,cr));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    List<String> roles = userDetails.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());

	    JwtResponse jwtResponse = new JwtResponse(jwt,
	            userDetails.getId(),
	            userDetails.getUsername(),
	            userDetails.getEmail(),
	            userDetails.getGenre(),
	            roles);

	    return ResponseEntity.ok(jwtResponse);*/
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		
		 //User user= (User) use;
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setOwner(user);
		//refreshTokenRepository.save(refreshToken);
		
		String accessToken = jwtUtils.generateAccessToken(user);
		String refreshTokenString = jwtUtils.generateRefreshToken(user, refreshToken.getId());
		
		return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SingnupRequest signUpRequest) {
		
	
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		

		/*long idd= this.userService.getUsers().size()+1;
		String Id= Long.toString(idd);*/
		// Create new user's account
		User user = new User(//singnupRequest.getId(),
				            // Id,
				             signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getGenre()
							 );

		Role ROLE_ADMIN= new Role(ERole.ROLE_ADMIN);
		Role ROLE_USER= new Role(ERole.ROLE_USER);
		if(this.roleService.lista().size()== 0) {
		 this.roleService.createRole(ROLE_ADMIN);}
		if(this.roleService.listu().size()== 0) {
		 this.roleService.createRole(ROLE_USER);}
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles.isEmpty()) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
			//roleRepository.save(userRole);
			//Role userRole=new Role(ERole.ROLE_USER);
			roles.add(userRole);
			
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setOwner(user);
		//refreshTokenRepository.save(refreshToken);
		
		String accessToken = jwtUtils.generateAccessToken(user);
		String refreshTokenString = jwtUtils.generateRefreshToken(user, refreshToken.getId());
		
		return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));

		//return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	

	@PostMapping("/logout")
	public ResponseEntity<HttpMessageConverter<?>> logout(@RequestBody TokenDTO dto) {
		String refreshTokenString = dto.getRefreshToken();
		if(jwtUtils.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString))) {
			// existe dans la base de donnée 
			refreshTokenRepository.deleteById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString));
			return ResponseEntity.ok().build();
		}
		
		throw new BadCredentialsException("Invalid token");
	}
	
	

	@PostMapping("/logout-all")
	public ResponseEntity<HttpMessageConverter<?>> logoutAll(@RequestBody TokenDTO dto) {
		String refreshTokenString = dto.getRefreshToken();
		if(jwtUtils.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString))) {
			// existe dans la base de donnée 
			refreshTokenRepository.deleteByOwner_id(jwtUtils.getUserIdFromRefreshToken(refreshTokenString));
			return ResponseEntity.ok().build();
		}
		
		throw new BadCredentialsException("Invalid token");
	}
	

	@PostMapping("/access-token")
	public ResponseEntity<?> accessToken(@RequestBody TokenDTO dto) {
		String refreshTokenString = dto.getRefreshToken();
		if(jwtUtils.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString))) {
			// existe dans la base de donnée
			User user = userDetailsServiceImpl.findById(jwtUtils.getUserIdFromRefreshToken(refreshTokenString));
			
			String accessToken = jwtUtils.generateAccessToken(user);
			return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
		}
		
		throw new BadCredentialsException("Invalid token");
	}
	
	
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody TokenDTO dto) {
		String refreshTokenString = dto.getRefreshToken();
		if(jwtUtils.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString))) {
			// existe dans la base de donnée
			
			refreshTokenRepository.deleteById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString));
			
			
			User user = userDetailsServiceImpl.findById(jwtUtils.getUserIdFromRefreshToken(refreshTokenString));
			RefreshToken refreshToken = new RefreshToken();
			refreshToken.setOwner(user);
			refreshTokenRepository.save(refreshToken);
			
			String accessToken = jwtUtils.generateAccessToken(user);
			String newRefreshTokenString = jwtUtils.generateRefreshToken(user, refreshToken.getId());
			
			return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, newRefreshTokenString));
		}
		
		throw new BadCredentialsException("Invalid token");
	}
	/*@PostMapping("/token-valide")
	public ResponseEntity<?> tokenv(@RequestBody TokenDTO dto){
		
		String refreshTokenString = dto.getRefreshToken();
		if(!jwtUtils.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString))) {
			// existe dans la base de donnée 
			refreshTokenRepository.deleteById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString));
			return ResponseEntity.ok("token non-valide");
		}
		//refreshTokenRepository.deleteById(jwtUtils.getTokenIdfromRefreshToken(refreshTokenString));
		throw new BadCredentialsException("valid token");
	}*/
	
	}
