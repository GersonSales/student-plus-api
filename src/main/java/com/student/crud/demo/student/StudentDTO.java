package com.student.crud.demo.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Credentials;
import com.student.crud.demo.property.Name;
import com.student.crud.demo.property.ProfileImage;

public class StudentDTO implements Convertible<StudentModel> {

  private String id;
  private Long registrationNumber;

  @JsonProperty("email")
  private String emailCredentials;

  private String passwordCredentials;

  private String firstName;
  private String lastName;

  @JsonProperty("street")
  private String streetAddress;

  @JsonProperty("city")
  private String cityAddress;

  @JsonProperty("state")
  private String stateAddress;

  private String profileImageName;
  private String profileImageUrl;

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

  @JsonIgnore
  public String getPasswordCredentials() {
    return passwordCredentials;
  }

  @JsonProperty("password")
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

  public String getProfileImageName() {
    return profileImageName;
  }

  public void setProfileImageName(final String profileImageName) {
    this.profileImageName = profileImageName;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  @Override
  public StudentModel convert() {
    final Credentials credentials = new Credentials(getEmailCredentials(), getPasswordCredentials());
    final Name name = new Name(getFirstName(), getLastName());
    final Address address = new Address(getStreetAddress(), getCityAddress(), getStateAddress());
    final ProfileImage profileImage = new ProfileImage(getProfileImageName(), getProfileImageUrl());

    return new StudentModel(
        getRegistrationNumber(),
        credentials,
        name,
        address,
        profileImage);
  }
}
