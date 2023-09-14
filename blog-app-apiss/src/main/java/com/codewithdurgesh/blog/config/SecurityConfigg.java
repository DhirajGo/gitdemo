package com.codewithdurgesh.blog.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.codewithdurgesh.blog.security.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfigg extends WebSecurityConfiguration  {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	
	@SuppressWarnings("removal")
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
		
		DefaultSecurityFilterChain build=http.build();
		return build;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
		
	}
	
}
