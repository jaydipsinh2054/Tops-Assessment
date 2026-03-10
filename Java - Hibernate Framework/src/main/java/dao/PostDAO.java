package dao;

import model.Post;
import java.util.List;

public interface PostDAO {

	boolean addPost(Post post);

	List<Post> getAllPosts();

	List<Post> getPostsByUser(int userId);

	Post getPostById(int id);

	boolean updatePost(Post post);

	boolean deletePost(int id);

	List<Post> searchByTitleOrTag(String keyword);

	List<Post> getRecentPosts(int limit);
}