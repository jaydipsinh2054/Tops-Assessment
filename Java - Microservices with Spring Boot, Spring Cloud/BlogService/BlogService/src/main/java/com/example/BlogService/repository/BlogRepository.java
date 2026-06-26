package com.example.BlogService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BlogService.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	List<Blog> findByTitleContainingIgnoreCase(String title);
}
