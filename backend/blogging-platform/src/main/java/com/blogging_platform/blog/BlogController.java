package com.blogging_platform.blog;

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


@RestController
@RequestMapping("/api/blog")
public class BlogController {
	
	@Autowired
	private BlogService service;
	
	@PostMapping("/")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		Blog savedBlog = service.createBlog(blog);
		return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
		Blog blog = service.getBlogById(id);
		return new ResponseEntity<>(blog, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Blog>> getAllBlogs(){
		List<Blog> blogs = service.getAllBlogs();
		return new ResponseEntity<>(blogs, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Blog>> getAllBlogsByUserId(@PathVariable Long id){
		List<Blog> blogs = service.getAllBlogsByUserId(id);
		return new ResponseEntity<>(blogs, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog){
		Blog updateBlog = service.updateBlog(id, blog);
		return new ResponseEntity<>(updateBlog, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBlogById(@PathVariable Long id){
		service.deleteBlogById(id);
		return ResponseEntity.noContent().build();
	}
}
