package com.blogging_platform.blog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.blogging_platform.exceptions.BlogNotFoundException;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository repo;
	
	public Blog createBlog(Blog blog) {
		Blog savedBlog = repo.save(blog);
		return savedBlog;
	}
	
	public Blog getBlogById(Long id) {
		Optional<Blog> optBlog = repo.findById(id);
		if(optBlog.isPresent()) {
			Blog blog = optBlog.get();
			return blog;
		}else {
			throw new BlogNotFoundException("Blog id "  + id + " is not found..!");
		}
	}
	
	public List<Blog> getAllBlogs(){
		List<Blog> blogs = repo.findAll();
		return blogs;
	}
	
	public List<Blog> getAllBlogsByUser(Long userId){
		List<Blog> blogs = repo.findByAuthorId(userId);
		return blogs;
	}
	
	public Blog updateBlog(Blog blog) {
		Optional<Blog> optBlog = repo.findById(blog.getId());
		if(optBlog.isPresent()) {
			Blog update = optBlog.get();
			update.setTitle(blog.getTitle());
			update.setContent(blog.getContent());
			update.setTimestamp(LocalDateTime.now());
			update.setStatus(blog.getStatus());
			return repo.save(update);
		}else {
			throw new BlogNotFoundException("Blog id "  + blog.getId() + " is not found..!");
		}
	}
	
	public void deleteBlogById(Long id) {
		try {
			repo.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new BlogNotFoundException("Blog id "  + id + " is not found..!");
		}
	}
}
