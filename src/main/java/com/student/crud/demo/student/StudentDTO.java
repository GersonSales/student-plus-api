package com.student.crud.demo.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Credentials;
import com.student.crud.demo.property.Name;

public class StudentDTO implements Convertible<StudentModel> {

  private String id;
  private Long registrationNumber;

  @JsonProperty("email")
  private String emailCredentials;

  @JsonProperty("password")
  private String passwordCredentials;

  private String firstName;
  private String lastName;

  @JsonProperty("street")
  private String streetAddress;

  @JsonProperty("city")
  private String cityAddress;

  @JsonProperty("state")
  private String stateAddress;

  public StudentDTO() {
  }

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

  public String getEmailCredentials() {
    return emailCredentials;
  }

  public void setEmailCredentials(String emailCredentials) {
    this.emailCredentials = emailCredentials;
  }

  public String getPasswordCredentials() {
    return passwordCredentials;
  }

  public void setPasswordCredentials(String passwordCredentials) {
    this.passwordCredentials = passwordCredentials;
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


  public void setStreetAddress(final String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCityAddress() {
    return cityAddress;
  }

  public void setCityAddress(final String cityAddress) {
    this.cityAddress = cityAddress;
  }

  public String getStateAddress() {
    return stateAddress;
  }

  public void setStateAddress(String stateAddress) {
    this.stateAddress = stateAddress;
  }

  @Override
  public StudentModel convert() {
    final Credentials credentials = new Credentials(getEmailCredentials(), getPasswordCredentials());
    final Name name = new Name(getFirstName(), getLastName());
    final Address address = new Address(getStreetAddress(), getCityAddress(), getStateAddress());

    return new StudentModel(getRegistrationNumber(), credentials, name, address);
  }
}
