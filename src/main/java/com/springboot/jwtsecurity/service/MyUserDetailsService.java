package com.springboot.jwtsecurity.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.jwtsecurity.model.User;
import com.springboot.jwtsecurity.repository.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{

	
		@Autowired
		public UserRepository userRepository;
		
		
		@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User userInfo = userRepository.getUserByUsername(username);
	                //.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	 
	        return new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(),
	                Collections.singletonList(new SimpleGrantedAuthority(userInfo.getRole())));
	    }
		
}
