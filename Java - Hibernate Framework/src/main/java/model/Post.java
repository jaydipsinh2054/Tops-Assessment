package model;

import java.sql.Timestamp;

public class Post {
	private int id;
	private int userId;
	private String title;
	private String content;
	private String tags;
	private Timestamp createdAt;

	public Post() {
	}

	public Post(int userId, String title, String content, String tags) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.tags = tags;
	}

	public Post(int id, int userId, String title, String content, String tags, Timestamp createdAt) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.createdAt = createdAt;
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getTags() {
		return tags;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}
}