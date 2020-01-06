package com.eSales.microserviceModel.dto;

public class ArticleObjectDto {

    private String category;

    private String type;

    private int saleNumber;

    private double price;

    private String brand;

    private String color;

    private String comment;

    // constructor

    public ArticleObjectDto() {
    }

    public ArticleObjectDto(String category, String type, int saleNumber, double price, String brand, String color, String comment) {
        this.category = category;
        this.type = type;
        this.saleNumber = saleNumber;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.comment = comment;
    }

    // getter and setters
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

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
