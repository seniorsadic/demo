package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
     public void globalConfig(AuthenticationManagerBuilder auth,DataSource data) throws Exception {
	   auth.jdbcAuthentication()
	   .dataSource(data)
	   .usersByUsernameQuery("select username as principal, password as credentials, true from bale.users where username=?")
	   .authoritiesByUsernameQuery("select users_username as principal, roles_role as role from bale.users_roles where users_username=?")
	   .passwordEncoder(new BCryptPasswordEncoder())
	   .rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/acura/**", "/imperial/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/index")
		.permitAll();
	}
}
