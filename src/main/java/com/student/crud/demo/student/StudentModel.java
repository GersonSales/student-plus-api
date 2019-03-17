package com.student.crud.demo.student;

import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.contract.Updatable;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Credentials;
import com.student.crud.demo.property.Name;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class StudentModel implements Convertible<StudentDTO>, Updatable<StudentModel> {

  @Id
  @NotNull
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private Long registrationNumber;
  private Credentials credentials;
  private Name name;
  private Address address;

  public StudentModel() {
  }

  /*default*/ StudentModel(final Long registrationNumber,
                           final Credentials credentials,
                           final Name name,
                           final Address address) {
    this.registrationNumber = registrationNumber;
    this.credentials = credentials;
    this.name = name;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getEmail() {
    return getCredentials().getEmail();
  }

  public void setEmail(String email) {
    getCredentials().setEmail(email);
  }

  public String getPassword() {
    return getCredentials().getPassword();
  }

  public void setPassword(String password) {
    getCredentials().setPassword(password);
  }

  public Credentials getCredentials() {
    return credentials;
  }


  public void setCredentials(Credentials credentials) {
    this.credentials = credentials;
  }

  public Name getName() {
    return name;
  }

  public void setName(final Name name) {
    this.name = name;
  }

  public Long getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(final Long registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(final Address address) {
    this.address = address;
  }

  @Override
  public StudentDTO convert() {
    final ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(this, StudentDTO.class);
  }

  @Override
  public void update(final StudentModel update) {
    setRegistrationNumber(update.getRegistrationNumber());
    setCredentials(update.getCredentials());
    setAddress(update.getAddress());
    setName(update.getName());
  }
}
