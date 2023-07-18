package com.student.api.service;

import com.student.api.dto.StudentDto;

import java.util.List;

public interface StudentService {

  /**
   * Loads all students based on page number and limit params
   * @param page
   * @param limit
   * @return
   */
  List<StudentDto> getStudents(int page, int limit);

  /**
   * Creates a new student
   * @param studentDto
   * @return
   */
  StudentDto createStudent(StudentDto studentDto);


  /**
   * Updates an existing student
   * @param studentDto
   * @param id
   * @return
   */
  StudentDto updateStudent(StudentDto studentDto, Long id);

  /**
   * Deletes a student based on id
   * @param id
   */
  void deleteStudent(Long id);


  /**
   * Loads a student based on id
   * @param id
   * @return
   */
  StudentDto findStudentById(Long id);
}
