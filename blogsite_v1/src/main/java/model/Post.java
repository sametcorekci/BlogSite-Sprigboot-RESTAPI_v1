package model;

import org.springframework.stereotype.Component;

@Component
public class Post {
    private Long id;
    private String name;
    private String surname;
    private String content;

    public Post(Long id, String name, String surname, String content, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.content = content;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;


}