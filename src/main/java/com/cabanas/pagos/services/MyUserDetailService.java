package com.cabanas.pagos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cabanas.pagos.models.User;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
    private UserService userService;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Optional<User> user = userService.findByUsername(username);
    	
    	//Si el usuario existe
    	if(user.isPresent()) {
    		
    		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new GrantedAuthority() {
            	
				/**
				 * JVM issue
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getAuthority() {
					return user.get().getRol();
				}
            });
            
    		return new org.springframework.security.core.userdetails.User(
    				user.get().getUsername(), "{noop}" + user.get().getPassword(), true, true, true, true, roles);
            
    		
    	} else {
    	    throw new UsernameNotFoundException("User not found");
    	}
    }
}