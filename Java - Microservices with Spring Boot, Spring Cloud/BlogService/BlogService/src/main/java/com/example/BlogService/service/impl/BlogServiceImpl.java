package com.example.BlogService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogService.entity.Blog;
import com.example.BlogService.repository.BlogRepository;
import com.example.BlogService.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
    private final BlogRepository blogRepo;

    public BlogServiceImpl(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public List<Blog> getAllBlogs(String search) {
        return (search != null)
                ? blogRepo.findByTitleContainingIgnoreCase(search)
                : blogRepo.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Blog updateBlog(Long id, Blog blog) {
        Blog existing = getBlogById(id);
        existing.setTitle(blog.getTitle());
        existing.setContent(blog.getContent());
        existing.setTags(blog.getTags());
        return blogRepo.save(existing);
    }

    public void deleteBlog(Long id) {
        blogRepo.deleteById(id);
    }
}