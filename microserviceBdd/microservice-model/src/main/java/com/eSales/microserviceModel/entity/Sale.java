package com.eSales.microserviceModel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id @GeneratedValue
    private int id;

    private String type;

    private String description;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    //constructor
    public Sale() {
    }

    public Sale(String type, String description, Date dateBegin, Date dateEnd, Address address) {
        this.type = type;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.address = address;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
        return "Sale{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", address=" + address +
                '}';
    }
}
