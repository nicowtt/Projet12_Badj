package com.esales.microservicemodel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleObjectDto {

    private int articleId;
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
}
