package com.esales.microservicemodel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String type;

    private String description;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
