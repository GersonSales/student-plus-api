package com.student.crud.demo.student;

import com.student.crud.demo.contract.Convertible;
import com.student.crud.demo.contract.Updatable;
import com.student.crud.demo.property.Address;
import com.student.crud.demo.property.Name;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StudentModel implements Convertible<StudentDTO>, Updatable<StudentModel> {

  @Id
  @NotNull
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private Long registrationNumber;
  private Name name;
  private Address address;

  public StudentModel() { }

  /*default*/ StudentModel(final Long registrationNumber,
                           final Name name,
                           final Address address) {
    this.registrationNumber = registrationNumber;
    this.name = name;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
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
    setAddress(update.getAddress());
    setName(update.getName());

  }
}
