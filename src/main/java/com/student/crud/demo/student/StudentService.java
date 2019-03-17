package com.student.crud.demo.student;

import com.student.crud.demo.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "studentService")
public class StudentService implements UserDetailsService {
  private final StudentRepository studentRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;


  public StudentService(final StudentRepository studentRepository,
                        final BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.studentRepository = studentRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    assert this.studentRepository != null;
    assert this.bCryptPasswordEncoder != null;
  }

  /*default*/ StudentDTO createStudent(final StudentDTO studentDTO) {
    studentDTO.setPasswordCredentials(bCryptPasswordEncoder.encode(studentDTO.getPasswordCredentials()));
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
    studentDTO.setPasswordCredentials(bCryptPasswordEncoder.encode(studentDTO.getPasswordCredentials()));
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

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    StudentModel modelByEmail = findModelByEmail(username);
    return new StudentPayload(modelByEmail.getId(), modelByEmail.getEmail(), modelByEmail.getPassword(), new ArrayList<>());
  }

  private StudentModel findModelByEmail(final String email) {
    final StudentModel studentModel = this.studentRepository.findByCredentials_Email(email);
    if (studentModel == null) {
      throw new UserNotFoundException();
    }
    return studentModel;
  }
}
