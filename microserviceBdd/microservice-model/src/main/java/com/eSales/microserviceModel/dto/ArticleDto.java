package com.eSales.microserviceModel.dto;

import com.eSales.microserviceModel.entity.*;
import com.eSales.microserviceModel.entity.Object;

import java.util.Date;

public class ArticleDto {
    private int id;
    private String category;
    private String type;
    private int saleNumber;
    private int price;
    private Date dateRecord;
    private boolean isValidateToSell;
    private boolean isSold;
    private boolean isStolen;
    private boolean isReturnOwner;
    private User user;
    private Sale sale;
    private Clothe clothe;
    private Toy toy;
    private Book book;
    private Object object;

    // constructor
    public ArticleDto() {
    }

    public ArticleDto(int id, String category, String type, int saleNumber, int price, Date dateRecord, boolean isValidateToSell, boolean isSold, boolean isStolen, boolean isReturnOwner, User user, Sale sale, Clothe clothe, Toy toy, Book book, Object object) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.saleNumber = saleNumber;
        this.price = price;
        this.dateRecord = dateRecord;
        this.isValidateToSell = isValidateToSell;
        this.isSold = isSold;
        this.isStolen = isStolen;
        this.isReturnOwner = isReturnOwner;
        this.user = user;
        this.sale = sale;
        this.clothe = clothe;
        this.toy = toy;
        this.book = book;
        this.object = object;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Clothe getClothe() {
        return clothe;
    }

    public void setClothe(Clothe clothe) {
        this.clothe = clothe;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    // to string
    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", saleNumber=" + saleNumber +
                ", price=" + price +
                ", dateRecord=" + dateRecord +
                ", isValidateToSell=" + isValidateToSell +
                ", isSold=" + isSold +
                ", isStolen=" + isStolen +
                ", isReturnOwner=" + isReturnOwner +
                ", user=" + user +
                ", sale=" + sale +
                ", clothe=" + clothe +
                ", toy=" + toy +
                ", book=" + book +
                ", object=" + object +
                '}';
    }
}
