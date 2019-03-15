package com.student.crud.demo.student;

import com.student.crud.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "studentService")
public class StudentService {
  private final StudentRepository studentRepository;


  public StudentService(final StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
    assert this.studentRepository != null;
  }

  /*default*/ StudentDTO createStudent(final StudentDTO studentDTO) {
    return save(studentDTO.convert()).convert();
  }

  /*default*/ List<StudentDTO> getAllStudents() {
    return this.studentRepository
        .findAll()
        .stream()
        .map(StudentModel::convert)
        .collect(Collectors.toList());
  }


  /*default*/ StudentDTO getStudentById(final String studentId) {
    return getModelById(studentId).convert();
  }

  private StudentModel getModelById(final String studentId) {
    Optional<StudentModel> foundStudent = this.studentRepository.findById(studentId);
    if (foundStudent.isPresent()) {
      return foundStudent.get();
    } else {
      throw new UserNotFoundException();
    }
  }


  /*default*/ void updateStudentById(final StudentDTO studentDTO) {
    final String studentId = studentDTO.getId();
    StudentModel foundStudent = getModelById(studentId);
    foundStudent.update(studentDTO.convert());
    save(foundStudent);
  }

  /*default*/ void deleteStudentById(final String studentId) {
    this.studentRepository.deleteById(studentId);
  }


  private StudentModel save(final StudentModel studentModel) {
    return this.studentRepository.save(studentModel);
  }

}
