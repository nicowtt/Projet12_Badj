package com.eSales.microserviceModel.entities.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
public class Clothe {

    @Id
    @Column(name = "id")
    private int articleId;

    private String size;

    private String gender;

    private String material;

    private String color;

    private String comment;

    @OneToOne
    @JsonBackReference
    @MapsId
    private Article article;

    // constructor
    public Clothe() {
    }

    public Clothe(int articleId, String size, String gender, String material, String color, String comment, Article article) {
        this.articleId = articleId;
        this.size = size;
        this.gender = gender;
        this.material = material;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
}
