package com.student.api.service.impl;

import com.student.api.dto.StudentDto;
import com.student.api.entity.Student;
import com.student.api.exception.StudentNotFoundException;
import com.student.api.repository.StudentRepository;
import com.student.api.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("studentService")
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  @Override
  public List<StudentDto> getStudents(int page, int limit) {
    if (page > 0) {
      page--;
    }

    PageRequest studentsPage = PageRequest.of(page, limit);
    Page<Student> allStudents = studentRepository.findAll(studentsPage);
    ModelMapper mapper = new ModelMapper();
    List<StudentDto> students = allStudents
            .stream()
            .map((studentEntity) -> mapper.map(studentEntity, StudentDto.class))
            .collect(Collectors.toList());

    return students;
  }

  @Override
  public StudentDto createStudent(StudentDto studentDto) {
    ModelMapper mm = new ModelMapper();
    Student newStudent = mm.map(studentDto, Student.class);

    studentRepository.save(newStudent);

    StudentDto createdValue = mm.map(newStudent, StudentDto.class);

    return createdValue;
  }

  @Override
  public StudentDto updateStudent(StudentDto studentDto, Long id) {
    ModelMapper mm = new ModelMapper();
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

    student.setBirthDate(studentDto.getBirthDate());
    student.setFirstName(studentDto.getFirstName());
    student.setLastName(studentDto.getLastName());
    Student updatedStudent = studentRepository.save(student);

    StudentDto returnedValue = mm.map(updatedStudent, StudentDto.class);

    return returnedValue;
  }

  @Override
  public void deleteStudent(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    studentRepository.delete(student);
  }

  @Override
  public StudentDto findStudentById(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    ModelMapper mm = new ModelMapper();
    StudentDto loadedValue = mm.map(student, StudentDto.class);
    return loadedValue;
  }

}
