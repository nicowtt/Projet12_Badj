package com.esales.microservicemodel.dto;

import com.esales.microservicemodel.entity.*;
import com.esales.microservicemodel.entity.Object;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
}
