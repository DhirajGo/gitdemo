package com.codewithdurgesh.blog.security;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//get token
		String requestToken = request.getHeader("Autherization");
		System.out.println(requestToken);
		
		String username=null;
		String token=null;
		
		if(request!= null && requestToken.startsWith("Bearer"))
		{
			 token = requestToken.substring(7);
			 try
			 {
			 username = this.jwtTokenHelper.getUserNameFromToken(token);
			 }
			 catch(IllegalArgumentException e)
			 {
				 System.out.println("Unable to get jwt token");
			 }
			 catch(ExpiredJwtException e)
			 {
				 System.out.println("jwt token has expired..");
			 }
			 catch(MalformedJwtException e)
			 {
				 System.out.println("invalid jwt");
			 }
		}
		else
		{
			System.out.println("jwt is not start with Bearer");
		}
		
		//once we get token we validate
		
		
		
	}

}
