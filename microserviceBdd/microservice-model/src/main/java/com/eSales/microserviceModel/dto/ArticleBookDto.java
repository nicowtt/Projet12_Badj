package com.eSales.microserviceModel.dto;

public class ArticleBookDto {

    private String category;

    private String type;

    private String name;

    private String author;

    private double price;

    private String comment;

    // constructor
    public ArticleBookDto() {
    }

    public ArticleBookDto(String category, String type, String name, String author, double price, String comment) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.author = author;
        this.price = price;
        this.comment = comment;
    }

    // getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
