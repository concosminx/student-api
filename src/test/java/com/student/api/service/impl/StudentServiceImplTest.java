package com.student.api.service.impl;

import com.student.api.dto.StudentDto;
import com.student.api.entity.Student;
import com.student.api.exception.StudentNotFoundException;
import com.student.api.repository.StudentRepository;
import com.student.api.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

  @Mock
  StudentRepository studentRepository;

  @InjectMocks
  StudentServiceImpl studentService;

  private Student studentMock;

  @BeforeEach
  void setUp() {
    studentMock = new Student();
    studentMock.setId(1L);
    studentMock.setLastName("Doe");
    studentMock.setFirstName("John");
    studentMock.setBirthDate(LocalDate.of(2000,01,01));
  }

  @Test
  @DisplayName("Checks if the find all students method is working")
  final void getStudents() {
    PageRequest pageable = PageRequest.of(0, 1);
    Page<Student> pageOfStudents = new PageImpl<>(List.of(studentMock), pageable, 0);

    when(studentRepository.findAll(any(PageRequest.class))).thenReturn(pageOfStudents);

    List<StudentDto> students = studentService.getStudents(0, 2);

    assertTrue(students.size() == 1);
    assertEquals("John", students.get(0).getFirstName());
  }

  @Test
  @DisplayName("Check the findStudentById - Ok Case")
  final void findStudentById() {
    when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(studentMock));

    StudentDto studentById = studentService.findStudentById(1L);

    assertNotNull(studentById);
    assertEquals("John", studentById.getFirstName());
  }


  @Test
  @DisplayName("Check the findStudentById - Not Found Case")
  final void findStudentById_StudentNotFound() {
    when(studentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

    assertThrows(StudentNotFoundException.class,
            () -> {
              studentService.findStudentById(1L);
            }
    );
  }

  @Test
  @DisplayName("Check create a new Student")
  final void createNewStudent() {
    when(studentRepository.save(any(Student.class))).thenReturn(studentMock);

    StudentDto studentDto = new StudentDto();
    studentDto.setLastName("Doe");
    studentDto.setFirstName("John");
    studentDto.setBirthDate(LocalDate.of(2000,01,01));

    StudentDto savedStudent = studentService.createStudent(studentDto);
    assertNotNull(savedStudent);
    assertEquals(studentMock.getFirstName(), savedStudent.getFirstName());
    assertEquals(studentMock.getLastName(), savedStudent.getLastName());

    verify(studentRepository, times(1)).save(any(Student.class));
  }
}
