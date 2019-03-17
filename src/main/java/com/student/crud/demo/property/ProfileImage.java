package com.student.crud.demo.property;

import javax.persistence.Embeddable;

@Embeddable
public final class ProfileImage {
  private String name;
  private String url;

  public ProfileImage() {
  }

  public ProfileImage(final String name, final String url) {
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
