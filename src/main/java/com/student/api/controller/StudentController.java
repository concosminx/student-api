package com.student.api.controller;

import com.student.api.dto.StudentDto;
import com.student.api.entity.Student;
import com.student.api.exception.StudentNotFoundException;
import com.student.api.repository.StudentRepository;
import com.student.api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @Operation(summary = "Returns the list of students")
  /*@ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Ok", content =
                  {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                          array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))}),

  })*/
  @GetMapping(produces = {
          MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE
  })
  List<StudentDto> getStudents(
          @Parameter(name = "page", description = "Page number, indexed from 1")
          @RequestParam(value = "page", defaultValue = "0") int page,
          @Parameter(name = "limit", description = "Page size")
          @RequestParam(value = "limit", defaultValue = "2") int limit
  ) {
    List<StudentDto> students = studentService.getStudents(page, limit);
    return students;
  }


  @Operation(summary = "Creates a new student")
  @PostMapping(
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
  )
  ResponseEntity<?> newStudent(@Parameter(description = "Student details", schema = @Schema(implementation = StudentDto.class))
                               @RequestBody(required = true) StudentDto studentDetails) {
    StudentDto student = studentService.createStudent(studentDetails);
    return ResponseEntity.status(HttpStatus.CREATED).body(student);
  }

  @Operation(summary = "Get a student by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the student"),
          @ApiResponse(responseCode = "404", description = "Student not found",
                  content = @Content)})
  @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  StudentDto one(@Parameter(name = "id", description = "The student id") @PathVariable Long id) {
    return studentService.findStudentById(id);
  }

  @Operation(summary = "Updates an existing student")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the student"),
          @ApiResponse(responseCode = "404", description = "Student not found",
                  content = @Content)})
  @PutMapping("/{id}")
  ResponseEntity<?> updateStudent(@RequestBody StudentDto newStudent,
                                  @PathVariable Long id) {
    StudentDto studentDto = studentService.updateStudent(newStudent, id);
    return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
  }

  @Operation(summary = "Deletes a student based on id")
  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
