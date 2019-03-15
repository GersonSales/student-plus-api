package com.student.crud.demo.property;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
  private String street;
  private String city;

  public Address() { }

  public Address(final String street, final String city) {
    this.street = street;
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
