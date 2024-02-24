package com.prenotapp._model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shop_name", nullable = false, length = 100)
    private String shopName;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "website", nullable = false, length = 100)
    private String website;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories;
}
