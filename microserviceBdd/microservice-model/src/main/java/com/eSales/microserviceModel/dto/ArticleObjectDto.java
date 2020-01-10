package com.eSales.microserviceModel.dto;

import java.util.Date;

public class ArticleObjectDto {

    private String category;

    private String type;

    private int saleId;

    private double price;

    private String brand;

    private String color;

    private String comment;

    private Date recordDate;

    private boolean isValidateToSell;

    private boolean isSold;

    private boolean isStolen;

    private boolean isReturnOwner;

    private String userEmail;

    // constructor

    public ArticleObjectDto() {
    }

    public ArticleObjectDto(String category, String type, int saleId, double price, String brand, String color, String comment, Date recordDate, boolean isValidateToSell, boolean isSold, boolean isStolen, boolean isReturnOwner, String userEmail) {
        this.category = category;
        this.type = type;
        this.saleId = saleId;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.comment = comment;
        this.recordDate = recordDate;
        this.isValidateToSell = isValidateToSell;
        this.isSold = isSold;
        this.isStolen = isStolen;
        this.isReturnOwner = isReturnOwner;
        this.userEmail = userEmail;
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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
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

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public boolean isValidateToSell() {
        return isValidateToSell;
    }

    public void setValidateToSell(boolean validateToSell) {
        isValidateToSell = validateToSell;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isStolen() {
        return isStolen;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }

    public boolean isReturnOwner() {
        return isReturnOwner;
    }

    public void setReturnOwner(boolean returnOwner) {
        isReturnOwner = returnOwner;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // to string
    @Override
    public String toString() {
        return "ArticleObjectDto{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", saleId=" + saleId +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", comment='" + comment + '\'' +
                ", recordDate=" + recordDate +
                ", isValidateToSell=" + isValidateToSell +
                ", isSold=" + isSold +
                ", isStolen=" + isStolen +
                ", isReturnOwner=" + isReturnOwner +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
