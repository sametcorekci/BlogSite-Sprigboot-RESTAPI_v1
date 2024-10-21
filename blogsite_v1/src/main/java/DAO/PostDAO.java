package DAO;

import model.Post;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PostDAO {
    private final String URL = "jdbc:mysql://localhost:3306/usertable";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";

    public void createPost(Post post) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO posts (name,surname,content,email) VALUES (?,?,?,?)")) {
            stmt.setString(1, post.getName());
            stmt.setString(2, post.getSurname());
            stmt.setString(3, post.getContent());
            stmt.setString(4, post.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<Post> getAllPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Post post = new Post(rs.getLong("id"), rs.getString("name"),
                        rs.getString("surname"), rs.getString("content"), rs.getString("email"));
                posts.add(post);
            }
        }
        return posts;
    }

    public boolean deletePost(String email) {
        String sql = "DELETE FROM posts WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePost(Long id, String name, String surname, String content, String email) {
        String sql = "UPDATE posts set name = ?, surname = ?, content = ?, email = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, content);
            statement.setString(4, email);
            statement.setLong(5, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Post güncellenirken hata oluştu ID: " + id);
            e.printStackTrace();
            return false;
        }
    }


}

