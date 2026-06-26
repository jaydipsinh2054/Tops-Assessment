package com.example.BlogService.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.BlogService.entity.Blog;
import com.example.BlogService.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ✅ CREATE BLOG
    @PostMapping(consumes = "multipart/form-data")
    public Blog createBlog(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tags") String tags,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setTags(tags);

        if (image != null && !image.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            image.transferTo(new File(uploadDir + fileName));

            blog.setImagePath("/uploads/" + fileName);
        }

        return blogService.createBlog(blog);
    }

    @GetMapping
    public List<Blog> getBlogs(@RequestParam(required = false) String search) {
        return blogService.getAllBlogs(search);
    }

    @GetMapping("/{id}")
    public Blog getBlog(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        return blogService.updateBlog(id, blog);
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "Blog deleted successfully";
    }
}
