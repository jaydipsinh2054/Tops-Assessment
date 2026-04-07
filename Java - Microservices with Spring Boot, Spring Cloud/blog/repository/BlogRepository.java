package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTitleContaining(String keyword);
}
