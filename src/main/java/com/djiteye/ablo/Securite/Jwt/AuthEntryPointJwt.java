package com.djiteye.ablo.Securite.Jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


@Component
@Log4j2
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	/*private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
	  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	    @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	        logger.error("Unauthorized error: {}", authException.getMessage());
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
	        response.getWriter().write("{\"error\":\"Unauthorized\"}");
	    }*/
	private static final Logger log = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	 @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	        log.error("Unauthorized error: {}", authException.getMessage());
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );

	 }

}
