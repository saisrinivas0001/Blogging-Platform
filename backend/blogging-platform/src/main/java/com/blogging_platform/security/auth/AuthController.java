package com.blogging_platform.security.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging_platform.User.CustomUserDetailsService;
import com.blogging_platform.User.User;
import com.blogging_platform.User.UserRepository;
import com.blogging_platform.enum_class.Role;
import com.blogging_platform.security.jwt.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request){
	    Optional<User> opt = userRepo.findUserByUsername(request.getUsername());
	    if(!opt.isPresent()) {
	        User user = new User();
	        user.setUsername(request.getUsername());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole(Role.USER);
	        user.setEmail(request.getEmail());
	        
	        userRepo.save(user);
	        return ResponseEntity.ok("User Register Successfully..!");
	    } else {
	        return new ResponseEntity<>("User Already Exist..!", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
		Authentication authentication = authenticationManager.authenticate(
												new UsernamePasswordAuthenticationToken(
																	request.getUsername(),
																	request.getPassword()));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = jwtService.generateToken(userDetails);
		
		String role = userDetails.getAuthorities().iterator().next().getAuthority();
		
		AuthResponse authResponse = new AuthResponse(token, role);
		return ResponseEntity.ok(authResponse);
		
	}
	
}
