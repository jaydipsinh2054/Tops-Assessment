package com.blog.controller;

import java.io.IOException;
import java.nio.file.*;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;


import com.blog.entity.Blog;
import com.blog.service.BlogService;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final UserService userService;

    @Autowired
    private BlogService blogService;

    BlogController(UserService userService) {
        this.userService = userService;
    }

    private String uploadImage(MultipartFile file) throws IOException {

        String folder = System.getProperty("user.dir") + "/uploads/";

        // ✅ Create folder if not exist
        Files.createDirectories(Paths.get(folder));

        String fileName = file.getOriginalFilename();

        Path path = Paths.get(folder + fileName);

        Files.write(path, file.getBytes());

        return path.toString();
    }

    @PostMapping("/blogs")
    public Blog createBlog(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String tags,
            @RequestParam MultipartFile image,
            Authentication authentication) throws IOException {

        String email = authentication.getName();

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setTags(tags);
        blog.setImagePath(uploadImage(image));

        blogService.setAuthor(blog, email);

        return blogService.save(blog);
    }

    
    @GetMapping("/blogs")
    public Object getBlogs(){
        return blogService.getAll();
    }

    @GetMapping("/blogs/{id}")
    public Blog getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @PutMapping("/blogs/{id}")
    public Blog updateBlog(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String tags,
            @RequestParam(required = false) MultipartFile image,
            Authentication authentication) throws IOException {

        Blog blog = blogService.findById(id);

        blog.setTitle(title);
        blog.setContent(content);
        blog.setTags(tags);

        if(image != null && !image.isEmpty()){
            blog.setImagePath(uploadImage(image));
        }

        return blogService.save(blog);
    }

    
    @DeleteMapping("/blogs/{id}")
    public String deleteBlog(@PathVariable Long id){

        blogService.deleteBlog(id);
        return "Blog Deleted Successfully";
    }
  

}
