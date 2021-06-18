package com.nic.pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nic.pms.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	 UserDetailsServiceImpl userDetailsService;
	 
	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
	 

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		
		authenticationMgr.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
   @Override
   protected void configure(HttpSecurity http) throws Exception {
   	http.authorizeRequests()
   	.antMatchers(
   			"/",
            "/js/**",
            "/css/**",
            "/images/**",            
   			"/login").permitAll()
   	.and().logout()     
	.logoutUrl("/pms-logout") 
	.logoutSuccessUrl("/login")
   	.and().exceptionHandling().accessDeniedPage("/error");
   	
   	
   }
   
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       return bCryptPasswordEncoder;
   }
	
}    