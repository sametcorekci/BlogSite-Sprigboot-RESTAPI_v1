package model;

import java.sql.Timestamp;

public class Message {
    private Long id;
    private String content;
    private Timestamp createdAt;
    public Message(Long id, String content, Timestamp createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}