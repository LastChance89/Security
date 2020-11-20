package com.sec.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sec.filter.RequestFilter;
import com.sec.service.impl.CustomUserDetailsServiceImpl;




@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  
{
	@Autowired 
	private CustomUserDetailsServiceImpl userDetailsService;
	 
	@Autowired 
	private RequestFilter requestFilter;
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {;		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		//auth.eraseCredentials(false);
	}
    	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 *assets, and dist are added to permit all so Angular can load properly. 
	 *Login page and login controller route are also unprotected. 
	 *Custom requestFilter added to the end, fires off on all events minus those with permit all
	 */
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		 http
		 .csrf().disable()
		 .authorizeRequests()
		 .antMatchers("/#/login").permitAll()
		 .antMatchers("/#/account").permitAll()
		 .antMatchers("/").permitAll()
		 .antMatchers("/assets/*").permitAll()
		 .antMatchers("/dist/*").permitAll()
		 .antMatchers("/power/authorization/userLogin").permitAll()  
		 .antMatchers("/power/authorization/createAccount").permitAll()
		 .antMatchers("/power/checkLogin/checkLoggedIn").permitAll()
		 .antMatchers("/power/authorization/getHint").permitAll()
		 .anyRequest().authenticated()
		 .and()
		 .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);	 
	  }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();

	}
	
}
