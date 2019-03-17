package com.student.crud.demo.student;

import com.student.crud.demo.exception.UserNotFoundException;
import com.student.crud.demo.file.FileStorageServiceImpl;
import com.student.crud.demo.file.UploadedFileResponse;
import com.student.crud.demo.property.ProfileImage;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "studentService")
public class StudentService implements UserDetailsService {
  private final StudentRepository studentRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final FileStorageServiceImpl fileStorageService;



  public StudentService(final StudentRepository studentRepository,
                        final BCryptPasswordEncoder bCryptPasswordEncoder,
                        final FileStorageServiceImpl fileStorageService) {
    this.studentRepository = studentRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.fileStorageService= fileStorageService;
    assert this.studentRepository != null;
    assert this.bCryptPasswordEncoder != null;
    assert this.fileStorageService != null;
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
    return findModelById(studentId).convert();
  }

  private StudentModel findModelById(final String studentId) {
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
    StudentModel foundStudent = findModelById(studentId);
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

  public void update(final StudentModel studentModel) {
    final StudentModel foundSeries = findModelById(studentModel.getId());
    foundSeries.update(studentModel);
    save(foundSeries);
  }


  /*default*/ UploadedFileResponse attachStudentProfileImage(final String seriesId, final MultipartFile file) {
    final StudentModel studentModel = findModelById(seriesId);
    final String fileName = fileStorageService.store(file, studentModel);

    final String fileDownloadUri = getFileUri(seriesId);

    final ProfileImage profileImage = new ProfileImage(fileName, fileDownloadUri);
    studentModel.setProfileImage(profileImage);
    this.update(studentModel);

    return new UploadedFileResponse(fileName, fileDownloadUri,
        file.getContentType(), file.getSize());
  }

  private String getFileUri(final String studentId) {
    return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/students/")
        .path(String.valueOf(studentId))
        .path("/profileImage")
        .toUriString();
  }

  /*default*/ Resource findSeriesCover(final String studentId) {
    return this.fileStorageService.loadAsResource(getStudentById(studentId).getProfileImageName());
  }

}
