package com.djiteye.ablo.Securite.Jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jline.utils.Log;

//import org.jline.utils.Log;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;*/
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;*/
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.djiteye.ablo.Entity.User;
//import com.djiteye.ablo.Securite.UserServiceDetailsImpl;


//import com.devback.uc.Entity.User;
//import com.devback.uc.Entity.User;
//import com.devback.uc.Securite.UserDetailsServiceImpl;



public class AuthTokenFilter extends OncePerRequestFilter {
	  @Autowired
	  private JwtUtils jwtUtils;

	 /* @Autowired
	  private UserServiceDetailsImpl userDetailsService;*/

	  @Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				Optional<String> accessToken = parseAccessToken(request);
				if(accessToken.isPresent() && jwtUtils.validateAccessToken(accessToken.get())) {
					/*String userId = jwtUtils.getUserIdFromAccessToken(accessToken.get());
					User user = userDetailsService.findById(userId);
					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, ((Authentication) user).getAuthorities());
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upat);*/
				}
			}catch(Exception e) {
				Log.error("cannot set authentication", e);
				
			}
			
			filterChain.doFilter(request, response);
		}
		
		private Optional<String> parseAccessToken(HttpServletRequest request) {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
				return Optional.of(authHeader.replace("Bearer ", ""));
			}
			return Optional.empty();
		}
	}