package com.student.crud.demo.property;

import javax.persistence.Embeddable;

@Embeddable
public final class Credentials {
  private String email;
  private String password;

  public Credentials() {
  }

  public Credentials(final String email, final String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
