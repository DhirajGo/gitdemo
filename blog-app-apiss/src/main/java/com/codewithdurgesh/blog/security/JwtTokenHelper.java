package com.codewithdurgesh.blog.security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY=5 * 60 *60;
	
	private String secret="jwtTokenKey";
	
	//retrive username from jwt token
	public String getUserNameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	//retrive expiration date
	public Date getExpirationDateFromToken(String token)
	{
		return (Date) getClaimFromToken(token,Claims::getExpiration);
	}
	
	
	public <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver)
	{
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//for retriving any information from token
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//cheak if token is expired
	private boolean isTokenExpired(String token)
	{
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new java.util.Date());
	}
	//generate token for user
	public String generateToken( UserDetails userDetails)
	{
		Map<String, Object>claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}

	  private String doGenerateToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret).compact();
	    }
	  
	  //validate token
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
