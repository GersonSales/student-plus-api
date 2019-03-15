package com.student.crud.demo.contract;

public interface Convertible<T extends Convertible> {
  T convert();
}
