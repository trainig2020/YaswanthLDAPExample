package com.yaswanth.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     
		auth.ldapAuthentication()
		           .userDnPatterns("uid={0},ou=people")
		           .groupSearchBase("ou=groups")
		           .userSearchFilter("uid={0}")
		           .groupSearchFilter("uniqueMember={0}")
		           .contextSource(contextSource())
		           .passwordCompare()
		           .passwordEncoder(new BCryptPasswordEncoder())
		           .passwordAttribute("userPassword");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.anyRequest().fullyAuthenticated()
		.and().formLogin();
	}
	@Bean
	public DefaultSpringSecurityContextSource contextSource(){
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"),"dc=springframework,dc=org");
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return (PasswordEncoder) new BCryptPasswordEncoder();
//	}
	
//	@Bean
//	public PasswordEncoder nopasswordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
}
 