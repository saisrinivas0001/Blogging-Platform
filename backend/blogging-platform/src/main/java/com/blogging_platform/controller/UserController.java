package com.blogging_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging_platform.User.User;
import com.blogging_platform.User.UserService;
import com.blogging_platform.blog.Blog;
import com.blogging_platform.blog.BlogService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	
	//blog-endpoints
	@PostMapping("/blog/")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		Blog savedBlog = blogService.createBlog(blog);
		return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
	}
	
	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
		Blog blog = blogService.getBlogById(id);
		return new ResponseEntity<>(blog, HttpStatus.OK);
	}
	
	@GetMapping("/blog/all")
	public ResponseEntity<List<Blog>> getAllBlogs(){
		List<Blog> blogs = blogService.getAllBlogs();
		return new ResponseEntity<>(blogs, HttpStatus.OK);
	}
	
	@GetMapping("/blog/user/{id}")
	public ResponseEntity<List<Blog>> getAllBlogsByUserId(@PathVariable Long id){
		List<Blog> blogs = blogService.getAllBlogsByUserId(id);
		return new ResponseEntity<>(blogs, HttpStatus.OK);
	}
	
	@PutMapping("/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog){
		Blog updateBlog = blogService.updateBlog(id, blog);
		return new ResponseEntity<>(updateBlog, HttpStatus.OK);
	}
	
	@DeleteMapping("/blog/{id}")
	public ResponseEntity<Void> deleteBlogById(@PathVariable Long id){
		blogService.deleteBlogById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	//user-endpoints
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		User updatedUser = userService.updateUser(id, user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User savedUser = userService.registerUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
}
