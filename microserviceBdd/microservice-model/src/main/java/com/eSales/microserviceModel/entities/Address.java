package com.eSales.microserviceModel.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    private int id;

    private String street;

    @Column(name = "postal_code")
    private int postalCode;

    private String city;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private User user;

    // constructor
    public Address() {
    }

    public Address(String street, int postalCode, String city, User user) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.user = user;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
