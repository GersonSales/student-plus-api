package com.student.crud.demo.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentModel, String> {
  @Override
  List<StudentModel> findAll();
}
