package com.esales.microservicemodel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
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
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
