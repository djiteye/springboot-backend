package com.djiteye.ablo.Securite.Jwt;


import java.util.Date;
import java.util.Optional;

//import org.jline.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.djiteye.ablo.Entity.User;
//import com.devback.uc.Securite.UserDetailsImpl;

/*import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;*/
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtUtils {
	
static final String issuer = "myApp";
private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

	
	@Value("#{${com.djiteye.ablo.accessTokenExpirationMinutes} * 24 * 60 * 60 * 1000}")
	private int accessTokenExpirationMs;
	
	//@Value("#{${refreshTokenExpirationDay} * 24 * 60 * 60 * 1000}")
	//private int refreshTokenExpirationDays;
	
	private long refreshTokenExpirationMs;

	private Algorithm accessTokenAlgorithm;
	private Algorithm refreshTokenAlgorithm;
	private JWTVerifier accessTokenVerifier;
	private JWTVerifier refreshTokenVerifier;
	
	public JwtUtils(@Value("${accessTokenSecret}") String accessTokenSecret, @Value("${refreshTokenSecret}") String refreshTokenSecret, @Value("${com.djiteye.ablo.refreshTokenExpirationDays}") int refreshTokenExpirationDays) {
		refreshTokenExpirationMs = (long) refreshTokenExpirationDays * 24 * 60 * 60 * 1000;
		accessTokenAlgorithm = Algorithm.HMAC512(accessTokenSecret);
		refreshTokenAlgorithm = Algorithm.HMAC512(refreshTokenSecret);
		accessTokenVerifier = JWT.require(accessTokenAlgorithm)
				.withIssuer(issuer)
				.build();
		refreshTokenVerifier = JWT.require(refreshTokenAlgorithm)
				.withIssuer(issuer)
				.build();
	}
	
	public String generateAccessToken(User user) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(user.getId())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(new Date().getTime() + accessTokenExpirationMs))
				.sign(accessTokenAlgorithm);
	}

	public String generateRefreshToken(User user, String tokenId) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(user.getId())
				.withClaim("tokenId", tokenId)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(new Date().getTime() + refreshTokenExpirationMs))
				.sign(refreshTokenAlgorithm);
	}
	
	private Optional<DecodedJWT> decodeAccessToken(String token) {
		try {
			return Optional.of(accessTokenVerifier.verify(token));
		}catch (JWTVerificationException e) {
			log.error("Invalid access token");
		}
		return Optional.empty();
	}
	
	private Optional<DecodedJWT> decodeRefreshToken(String token) {
		try {
			return Optional.of(refreshTokenVerifier.verify(token));
		}catch (JWTVerificationException e) {
			log.error("Invalid refresh token");
			//System.out.print(e);
			
		}
		return Optional.empty();
		
	}
	
	public boolean validateAccessToken(String token) {
		return decodeAccessToken(token).isPresent();
	}

	public boolean validateRefreshToken(String token) {
		return decodeRefreshToken(token).isPresent();
	}
	
	public String getUserIdFromAccessToken(String token) {
		return decodeAccessToken(token).get().getSubject();
	}

	public String getUserIdFromRefreshToken(String token) {
		return decodeRefreshToken(token).get().getSubject();
	}
	public String getTokenIdfromRefreshToken(String token) {
		return decodeRefreshToken(token).get().getClaim("tokenId").asString();
	}
}