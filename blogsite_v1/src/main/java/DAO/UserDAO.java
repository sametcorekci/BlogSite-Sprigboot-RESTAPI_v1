package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String URL = "jdbc:mysql://localhost:3306/usertable";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";

    public void createUser(User user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name,surname,email) VALUES (?,?,?)")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getLong("id"),rs.getString("name"),
                        rs.getString("surname"), rs.getString("email"));
                users.add(user);
            }
        }
        return users;
    }


    public boolean deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0; // Eğer silme işlemi başarılı olduysa true döner
        } catch (SQLException e) {
            System.err.println("Silinirken hata oluştu ID: " + id);
            e.printStackTrace();
            return false; // Hata durumunda false döner
        }
    }
}