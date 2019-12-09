package com.eSales.microserviceModel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    private int articleId;

    private String name;

    private String author;

    private String comment;

    @OneToOne
    @JsonBackReference
    @MapsId
    private Article article;

    // constructor
    public Book() {
    }

    public Book(int articleId, String name, String author, String comment, Article article) {
        this.articleId = articleId;
        this.name = name;
        this.author = author;
        this.comment = comment;
        this.article = article;
    }

    // getters and setters
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    // to string
    @Override
    public String toString() {
        return "Book{" +
                "articleId=" + articleId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", comment='" + comment + '\'' +
                ", article=" + article +
                '}';
    }
}

