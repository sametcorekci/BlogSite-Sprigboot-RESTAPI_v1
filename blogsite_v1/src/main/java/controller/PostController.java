package controller;

import DAO.PostDAO;
import model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/posts-api")
public class PostController {
    private PostDAO postDAO = new PostDAO();

    @PostMapping("/creted")
    public void createPost(@RequestBody Post post) throws SQLException {
        postDAO.createPost(post);
    }

    @GetMapping("/get-all")
    public List<Post> getAllPosts() throws SQLException {
        return postDAO.getAllPosts();
    }

    @DeleteMapping("/delete-posts/{email}")
    public ResponseEntity<String> deletePost(@PathVariable String email)
    {
        boolean isDeleted = postDAO.deletePost(email);
        if(isDeleted)
            return ResponseEntity.ok("ID: " + email + " başarıyla silindi.");
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID: " + email + " veritabanında bulunamadı veya silinmiş.");
        }
    }

    @PutMapping("/update-post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id,@RequestBody Post post)
    {
        boolean isUpdated = postDAO.updatePost(id, post.getName(), post.getSurname(), post.getContent(), post.getEmail());

        if (isUpdated)
            return  ResponseEntity.ok("Post başarıyla güncellendi.");
        else
            return ResponseEntity.status(404).body("Post ID: " + id + " veritabanında bulunamadı.");
    }







}