package com.eSales.microserviceModel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    private String email;

    private String phone;

    @Column(name = "is_voluntary")
    private boolean isVoluntary;

    @Column(name = "is_responsible")
    private boolean isResponsible;


    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // constructor
    public User() {
    }

    public User(String name, String lastName, String password, String email, String phone, boolean isVoluntary, boolean isResponsible, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isVoluntary = isVoluntary;
        this.isResponsible = isResponsible;
        this.address = address;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVoluntary() {
        return isVoluntary;
    }

    public void setVoluntary(boolean voluntary) {
        isVoluntary = voluntary;
    }

    public boolean isResponsible() {
        return isResponsible;
    }

    public void setResponsible(boolean responsible) {
        isResponsible = responsible;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // to string
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isVoluntary=" + isVoluntary +
                ", isResponsible=" + isResponsible +
                ", address=" + address +
                '}';
    }
}
