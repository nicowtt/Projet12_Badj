package com.eSales.microserviceModel.dto;

public class ArticleClotheDto {

    private String category;

    private String type;

    private int saleNumber;

    private double price;

    private String size;

    private String gender;

    private String material;

    private String color;

    private String comment;



    // Constructor
    public ArticleClotheDto() {
    }

    public ArticleClotheDto(String category, String type, int saleNumber, double price, String size, String gender, String material, String color, String comment) {
        this.category = category;
        this.type = type;
        this.saleNumber = saleNumber;
        this.price = price;
        this.size = size;
        this.gender = gender;
        this.material = material;
        this.color = color;
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
}
