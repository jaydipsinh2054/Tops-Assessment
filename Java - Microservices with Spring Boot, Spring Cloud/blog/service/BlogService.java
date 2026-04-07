package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repo;

    @Autowired
    private UserRepository userRepository;

    // ⭐ Save Blog
    public Blog save(Blog blog){
        return repo.save(blog);
    }

    // ⭐ Get All Blogs
    public List<Blog> getAll(){
        return repo.findAll();
    }

    // ⭐ Set Author
    public void setAuthor(Blog blog, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        blog.setAuthor(user);
    }

    // ⭐ Get Single Blog
    public Blog getBlogById(Long id){

        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Blog updateBlog(Long id, String title, String content, String tags){

        Blog blog = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setTitle(title);
        blog.setContent(content);
        blog.setTags(tags);

        return repo.save(blog);
    }


    // ⭐ Delete Blog (Only Author)
    public void deleteBlog(Long id){

        Blog blog = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        repo.delete(blog);
    }
    public Blog findById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }


    // ⭐ Search Blog
    public List<Blog> searchBlog(String keyword){

        if(keyword == null)
            return repo.findAll();

        return repo.findByTitleContaining(keyword);
    }
}
