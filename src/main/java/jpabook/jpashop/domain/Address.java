package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {


    private String City;
    private String street;
    private String zipCode;


    public Address() {
    }

    public Address(String city, String street, String zipCode) {
        City = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
