package com.blogging_platform.blog;

import java.time.LocalDateTime;

import com.blogging_platform.User.User;
import com.blogging_platform.enum_class.BlogStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String title;
	
	@NotBlank
	private String content;
	
	@ManyToOne()
	@JoinColumn(name = "users_id", nullable = false)
	private User author;
	
	@Enumerated(EnumType.STRING)
	private BlogStatus status = BlogStatus.PENDING;
	
	private LocalDateTime timestamp;

	public Blog() {
	}

	public Blog(Long id, String title, String content, User author, BlogStatus status) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.status = status;
	}
	
	@PrePersist
	public void prePersist() {
		this.timestamp = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public BlogStatus getStatus() {
		return status;
	}

	public void setStatus(BlogStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
