package com.eSales.microserviceModel.dto;

import java.util.Date;

public class ArticleClotheDto {

    private String category;

    private String type;

    private int saleId;

    private double price;

    private String size;

    private String gender;

    private String material;

    private String color;

    private String comment;

    private Date recordDate;

    private boolean isValidateToSell;

    private boolean isSold;

    private boolean isStolen;

    private boolean isReturnOwner;

    private String userEmail;



    // Constructor
    public ArticleClotheDto() {
    }

    public ArticleClotheDto(String category, String type, int saleId, double price, String size, String gender, String material, String color, String comment, Date recordDate, boolean isValidateToSell, boolean isSold, boolean isStolen, boolean isReturnOwner, String userEmail) {
        this.category = category;
        this.type = type;
        this.saleId = saleId;
        this.price = price;
        this.size = size;
        this.gender = gender;
        this.material = material;
        this.color = color;
        this.comment = comment;
        this.recordDate = recordDate;
        this.isValidateToSell = isValidateToSell;
        this.isSold = isSold;
        this.isStolen = isStolen;
        this.isReturnOwner = isReturnOwner;
        this.userEmail = userEmail;
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
        return "ArticleClotheDto{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", saleId=" + saleId +
                ", price=" + price +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", material='" + material + '\'' +
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
