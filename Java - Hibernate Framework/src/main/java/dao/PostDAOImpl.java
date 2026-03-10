package dao;

import model.Post;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {

	@Override
	public boolean addPost(Post post) {
		try {
			Connection con = DBConnection.getConnection();
			String sql = "INSERT INTO posts(user_id,title,content,tags) VALUES(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, post.getUserId());
			ps.setString(2, post.getTitle());
			ps.setString(3, post.getContent());
			ps.setString(4, post.getTags());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Post> getAllPosts() {

		List<Post> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM posts ORDER BY created_at DESC";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(extractPost(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Post> getPostsByUser(int userId) {

		List<Post> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM posts WHERE user_id=? ORDER BY created_at DESC";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(extractPost(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Post getPostById(int id) {

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM posts WHERE id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractPost(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updatePost(Post post) {

		try {
			Connection con = DBConnection.getConnection();
			String sql = "UPDATE posts SET title=?, content=?, tags=? WHERE id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getTags());
			ps.setInt(4, post.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deletePost(int id) {

		try {
			Connection con = DBConnection.getConnection();
			String sql = "DELETE FROM posts WHERE id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Post> searchByTitleOrTag(String keyword) {

		List<Post> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM posts WHERE title LIKE ? OR tags LIKE ? ORDER BY created_at DESC";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(extractPost(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Post> getRecentPosts(int limit) {

		List<Post> list = new ArrayList<>();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM posts ORDER BY created_at DESC LIMIT ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, limit);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(extractPost(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 🔹 Helper Method to Extract Post Object
	private Post extractPost(ResultSet rs) throws SQLException {

		return new Post(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("content"),
				rs.getString("tags"), rs.getTimestamp("created_at"));
	}
}