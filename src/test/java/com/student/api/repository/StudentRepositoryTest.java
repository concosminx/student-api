package com.student.api.repository;

import com.student.api.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentRepositoryTest {

  @Autowired
  StudentRepository studentRepository;

  static boolean recordsCreated = false;

  @BeforeEach
  void setUp() throws Exception {

    if(!recordsCreated) {
      createRecords();
    }
  }

  @Test
  @DisplayName("Test find all student method")
  final void findAll() {
    Pageable pageable = PageRequest.of(1, 1);
    Page<Student> all = studentRepository.findAll(pageable);

    assertNotNull(all);

    List<Student> content = all.getContent();
    assertNotNull(content);
    assertTrue(content.size() == 1);
  }


  @Test
  @DisplayName("Test find by last name")
  final void findByLastName() {
    List<Student> list = studentRepository.findByLastName("Doe");
    assertNotNull(list);
    assertTrue(list.size() == 2);
  }

  @Test
  @DisplayName("Test find by first name")
  final void findByFirstName() {
    List<Student> list = studentRepository.findByFirstName("Jane");
    assertNotNull(list);
    assertTrue(list.size() == 1);
  }

  private void createRecords() {
    Student s1 = new Student();
    s1.setLastName("Doe");
    s1.setFirstName("John");
    s1.setBirthDate(LocalDate.of(2000, 01,01));

    studentRepository.save(s1);

    Student s2 = new Student();
    s2.setLastName("Doe");
    s2.setFirstName("Jane");
    s2.setBirthDate(LocalDate.of(2002, 01,01));

    studentRepository.save(s2);

    recordsCreated = true;
  }


}
