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
@Table(name = "clothes")
public class Clothe {

    @Id
    @Column(name = "id")
    private int articleId;

    private String size;

    private String gender;

    private String material;

    private String color;

    private String comment;

    @OneToOne
    @JsonBackReference
    @MapsId
    private Article article;
}
