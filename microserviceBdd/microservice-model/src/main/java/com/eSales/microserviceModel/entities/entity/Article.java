package com.eSales.microserviceModel.entities.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    private String category;

    private String type;

    @Column(name = "sale_number")
    private int saleNumber;

    private double price;

    @Column(name = "date_record")
    private Date dateRecord;

    @Column(name = "is_validate_to_sell")
    private boolean isValidateToSell;

    @Column(name = "is_sold")
    private boolean isSold;

    @Column(name = "is_stolen")
    private boolean isStolen;

    @Column(name = "is_return_owner")
    private boolean isReturnOwner;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Clothe clothe;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Toy toy;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Book book;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Object object;

    // constructor
    public Article() {
    }

    public Article(String category, String type, int saleNumber, double price, Date dateRecord, boolean isValidateToSell, boolean isSold, boolean isStolen, boolean isReturnOwner, User user, Sale sale, Clothe clothe, Toy toy, Book book, Object object) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
}
