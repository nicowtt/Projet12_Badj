package com.eSales.microserviceModel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "objects")
public class Object {

    @Id
    @Column(name = "id")
    private int articleId;

    private String brand;

    private String color;

    private String comment;

    @OneToOne
    @JsonBackReference
    @MapsId
    private Article article;

    // constructor
    public Object() {
    }

    public Object(int articleId, String brand, String color, String comment, Article article) {
        this.articleId = articleId;
        this.brand = brand;
        this.color = color;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return "Object{" +
                "articleId=" + articleId +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", comment='" + comment + '\'' +
                ", article=" + article +
                '}';
    }
}
