package com.esales.microservicemodel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
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
}
