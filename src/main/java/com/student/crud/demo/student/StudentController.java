package com.student.crud.demo.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(final StudentService studentService) {
    this.studentService = studentService;
    assert this.studentService != null;
  }

  @PostMapping
  public ResponseEntity<StudentDTO> post(@RequestBody final StudentDTO studentDTO) {
    try {
      final StudentDTO createStudent = this.studentService.createStudent(studentDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
    } catch (final Throwable throwable) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<StudentDTO>> get() {
    try {
      final List<StudentDTO> allFoundStudents = this.studentService.getAllStudents();
      return ResponseEntity.ok(allFoundStudents);
    } catch (final Throwable throwable) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<StudentDTO> get(@PathVariable final String studentId) {
    try {
      final StudentDTO foundStudent = this.studentService.getStudentById(studentId);
      return ResponseEntity.ok(foundStudent);
    } catch (final Throwable throwable) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/{studentId}")
  public ResponseEntity<Void> put(@PathVariable final String studentId,
                                  @RequestBody final StudentDTO studentDTO) {
    try {
      studentDTO.setId(studentId);
      this.studentService.updateStudentById(studentDTO);
      return ResponseEntity.ok().build();
    } catch (final Throwable throwable) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{studentId}")
  public ResponseEntity<Void> delete(@PathVariable String studentId) {
    try {
      this.studentService.deleteStudentById(studentId);
      return ResponseEntity.ok().build();
    } catch (final Throwable throwable) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }

}
