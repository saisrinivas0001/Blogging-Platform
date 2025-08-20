package com.blogging_platform.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging_platform.exceptions.UserAlreadyExistException;
import com.blogging_platform.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public User registerUser(User user) {
		Optional<User> optUserByEmail = repo.findUserByEmail(user.getEmail());
		Optional<User> optUserByUsername = repo.findUserByUsername(user.getUsername());
		if(optUserByEmail.isEmpty() && optUserByUsername.isEmpty()) {
			return repo.save(user);
		}else {
			throw new UserAlreadyExistException("User Already Exist..!");
		}
	}
	
	public User getUserById(Long id) {
		Optional<User> optUser = repo.findById(id);
		if(optUser.isPresent()) {
			return optUser.get();
		}else {
			throw new UserNotFoundException("User id " + id + " is not found..!");
		}
	}
	
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	public User updateUser(Long id, User user) {
		Optional<User> optUser = repo.findById(id);
		if(optUser.isPresent()) {
			User update = optUser.get();
			update.setEmail(user.getEmail());
			update.setUsername(user.getUsername());
			update.setPassword(user.getPassword());
			update.setRole(user.getRole());
			return repo.save(update);
		}else {
			throw new UserNotFoundException("User id " + id + " is not found..!");
		}
	}
	
	public void deleteUser(Long id) {
		Optional<User> optUser = repo.findById(id);
		if(optUser.isPresent()) {
			repo.deleteById(id);
		}else {
			throw new UserNotFoundException("User id " + id + " is not found..!");
		}
	}
	
	
}
