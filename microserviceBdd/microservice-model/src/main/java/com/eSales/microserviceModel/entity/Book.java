package com.eSales.microserviceModel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    private int articleId;

    private String name;

    private String author;

    private String comment;

    @OneToOne
    @JsonBackReference
    @MapsId
    private Article article;
}

