package controller;
import DAO.UserDAO;

import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;@RestController
@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO = new UserDAO();

    @PostMapping("/creted-user")
    public void createUser(@RequestBody User user) throws SQLException {
        userDAO.createUser(user);
    }



    @GetMapping("/get-all")
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userDAO.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("ID: " + id + " başarıyla silindi.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID: " + id + " veritabanında bulunamadı veya silinmiş.");
        }
    }


}