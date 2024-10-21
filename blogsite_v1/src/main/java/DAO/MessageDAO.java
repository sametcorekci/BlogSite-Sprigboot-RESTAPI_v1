package DAO;

import model.Message;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MessageDAO {

    private final String URL = "jdbc:mysql://localhost:3306/usertable";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";


    public void createMessage(Message message) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO messages (content) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, message.getContent());
            stmt.executeUpdate();

            // Eklenen mesajın ID'sini alalım
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    message.setId(generatedKeys.getLong(1)); // Mesaj ID'sini set edelim
                }
            }
        }
    }

    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Message message = new Message(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at") // Ekleme tarihini alıyoruz
                );
                messages.add(message);
            }
        }
        return messages;
    }
    public List<Message> getIdMessages(Long id) throws SQLException {
        List<Message> messages = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages WHERE id = ?")) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message(rs.getLong("id"), rs.getString("content"), rs.getTimestamp("created_at"));
                    messages.add(message);
                }
            }
        }

        return messages;
    }
    public void deleteMessageById(Long id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Silinirken hata oluştu ID: " + id);
            e.printStackTrace();
        }
    }

}