package com.esales.microservicemodel.dto;

import com.esales.microservicemodel.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SaleDto {

    private int id;

    private String type;

    private String description;

    private Date dateBegin;

    private Date dateEnd;

    private Address address;

    private Integer nbrArticlesPreRecordForUser;
}
