package com.m3bi.hotelbooking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	   
	   @Autowired
	    private CustomAuthenticationProvider authProvider;
	   
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {;
		   http.httpBasic().and()
           .authorizeRequests()
           .anyRequest().authenticated().and().addFilterAfter(new PostAuthenticationFilter(), BasicAuthenticationFilter.class);
	   }
	   
	   @Autowired
	   public void configure(AuthenticationManagerBuilder auth) throws Exception{
		   auth.authenticationProvider(authProvider);
	    }
		
	}
