package com.student.crud.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Name;

public class StudentDTO implements Convertible<StudentModel> {

  private String id;
  private Long registrationNumber;

  private String firstName;
  private String lastName;

  private String streetAddress;
  private String cityAddress;

  public StudentDTO() { }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public Long getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(final Long registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  @JsonProperty("street")
  public void setStreetAddress(final String streetAddress) {
    this.streetAddress = streetAddress;
  }

  @JsonProperty("city")
  public String getCityAddress() {
    return cityAddress;
  }

  public void setCityAddress(final String cityAddress) {
    this.cityAddress = cityAddress;
  }

  @Override
  public StudentModel convert() {
    final Name name = new Name(getFirstName(), getLastName());
    final Address address = new Address(getStreetAddress(), getCityAddress());

    return new StudentModel(getRegistrationNumber(), name, address);
  }
}
