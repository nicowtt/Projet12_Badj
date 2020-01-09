package com.eSales.microserviceModel.dto;

import com.eSales.microserviceModel.entity.Address;

import java.util.Date;

public class SaleDto {

    private int id;

    private String type;

    private String description;

    private Date dateBegin;

    private Date dateEnd;

    private Address address;

    private Integer nbrArticlesPreRecordForUser;

    // constructor
    public SaleDto() {
    }

    public SaleDto(int id, String type, String description, Date dateBegin, Date dateEnd, Address address, Integer nbrArticlesPreRecordForUser) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.address = address;
        this.nbrArticlesPreRecordForUser = nbrArticlesPreRecordForUser;
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

    public Integer getNbrArticlesPreRecordForUser() {
        return nbrArticlesPreRecordForUser;
    }

    public void setNbrArticlesPreRecordForUser(Integer nbrArticlesPreRecordForUser) {
        this.nbrArticlesPreRecordForUser = nbrArticlesPreRecordForUser;
    }
}
