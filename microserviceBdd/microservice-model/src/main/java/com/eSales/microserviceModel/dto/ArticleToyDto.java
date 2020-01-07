package com.eSales.microserviceModel.dto;

public class ArticleToyDto {

    private String category;

    private String type;

    private String brand;

    private String color;

    private double price;

    private String comment;

    // constructor
    public ArticleToyDto() {
    }

    public ArticleToyDto(String category, String type, String brand, String color, double price, String comment) {
        this.category = category;
        this.type = type;
        this.brand = brand;
        this.color = color;
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
