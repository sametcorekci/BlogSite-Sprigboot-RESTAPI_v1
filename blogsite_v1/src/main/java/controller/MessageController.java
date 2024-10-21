package controller;

import DAO.MessageDAO;
import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageDAO messageDAO;

    @Autowired
    public MessageController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody Message message) throws SQLException {
        messageDAO.createMessage(message);
        return ResponseEntity.ok("Mesaj başarıyla oluşturuldu.");
    }

    @GetMapping("/all-message")
    public ResponseEntity<List<Message>> getAllMessages() throws SQLException {
        return ResponseEntity.ok(messageDAO.getAllMessages());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable Long id) {
        messageDAO.deleteMessageById(id);
        return ResponseEntity.ok("Mesaj başarıyla silindi.");
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<Message>> getIdMessages(@PathVariable Long id) throws SQLException {
        List<Message> messages = messageDAO.getIdMessages(id);
        return ResponseEntity.ok(messages);
    }
}
