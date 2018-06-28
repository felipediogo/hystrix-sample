package com.felipediogo.hystrix.repository;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Book")
public class Book implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
