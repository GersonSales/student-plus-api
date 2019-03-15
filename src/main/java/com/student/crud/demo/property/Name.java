package com.student.crud.demo.property;

import javax.persistence.Embeddable;

@Embeddable
public class Name {
  private String firstName;
  private String lastName;

  public Name() { }

  public Name(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
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
}
