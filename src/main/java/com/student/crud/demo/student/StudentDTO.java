package com.student.crud.demo.student;

import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Name;

public class StudentDTO implements Convertible<StudentModel> {

  private String id;
  private Long registrationNumber;

  private String firstName;
  private String lastName;

  private String street;
  private String city;

  public StudentDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(Long registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  @Override
  public StudentModel convert() {
    final Name name = new Name(getFirstName(), getLastName());
    final Address address = new Address(getStreet(), getCity());

    return new StudentModel(getRegistrationNumber(), name, address);
  }
}
