package com.student.api.service;

import com.student.api.dto.StudentDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface StudentService {

  /**
   * Loads all students based on page number and limit params
   * @param page
   * @param limit
   * @return
   */
  List<StudentDto> getStudents(@Min(0)
                               int page,
                               @Min(0)
                               int limit);

  /**
   * Creates a new student
   * @param studentDto
   * @return
   */
  StudentDto createStudent(@Valid StudentDto studentDto);


  /**
   * Updates an existing student
   * @param studentDto
   * @param id
   * @return
   */
  StudentDto updateStudent(@Valid StudentDto studentDto, @Min(1) Long id);

  /**
   * Deletes a student based on id
   * @param id
   */
  void deleteStudent(@Min(1) Long id);


  /**
   * Loads a student based on id
   * @param id
   * @return
   */
  StudentDto findStudentById(@Min(1) Long id);
}
