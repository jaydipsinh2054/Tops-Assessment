package com.example.BlogService.service;

import java.util.List;

import com.example.BlogService.entity.Blog;

public interface BlogService {

	Blog createBlog(Blog blog);
	List<Blog> getAllBlogs(String search);
	Blog getBlogById(Long id);
	Blog updateBlog(Long id, Blog blog);
	void deleteBlog(Long blog);
}
