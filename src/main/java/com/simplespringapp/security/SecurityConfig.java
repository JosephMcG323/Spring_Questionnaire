package com.simplespringapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

	//Auth user roles
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("secret1")
                .roles("USER").and().withUser("admin1").password("secret1")
                .roles("USER","ADMIN");
    }

	//Auth role ->access
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**")
        .hasRole("USER").antMatchers("/users/**").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
                .headers().frameOptions().disable(); //Config halBrowser
    }
}
		
	
	

	
	
	
	
	
