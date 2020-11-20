package com.sec.filter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sec.model.User;
import com.sec.util.AuthenticationTokenUtil;


@Component
public class RequestFilter extends OncePerRequestFilter {

	private final Logger logger = LogManager.getLogger(RequestFilter.class);

	@Autowired
	private AuthenticationTokenUtil authUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String userName = null;
		User user = null;
		//UserDetails userDetails = null;
	
		//We know the user is logged in and good to go. 
		//Verify this is working with the logged in user, not just what ever user is pinging the server. 
		if(SecurityContextHolder.getContext().getAuthentication() != null || request.getHeader("Authorization") == null) {
			filterChain.doFilter(request,response);	
		}
		else {
			
		String tokenHeader = request.getHeader("Authorization");
		
		try {
			//Make the user name token in order to 
			userName = authUtil.getUserNameFromToken(tokenHeader);
		}
		catch(Exception e) {
			logger.error("ERROR: ",e);
			
		}
		
		//&& SecurityContextHolder.getContext().getAuthentication() == null
		if (userName != null) { 
			user = new User(userName, authUtil.getRoles(tokenHeader));
			//userDetails = new User(userName, "", authUtil.getRoles(tokenHeader));
		}
		 
		if(authUtil.validate(tokenHeader, user)) {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
					user,null,user.getRoles());
			upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(upToken);
		}
	
		filterChain.doFilter(request,response);	
		}
	}


}
