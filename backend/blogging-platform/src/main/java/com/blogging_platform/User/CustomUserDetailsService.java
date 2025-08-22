package com.blogging_platform.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogging_platform.exceptions.UserNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username)
									.orElseThrow(
											()-> new UserNotFoundException("User not found..!")
											);
		
		return org.springframework.security.core.userdetails.User
															.withUsername(user.getUsername())
															.password(user.getPassword())
															.roles(user.getRole().name())
															.build();
	}
	


}
