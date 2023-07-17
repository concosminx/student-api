package com.student.api.rest;

import com.student.api.exceptions.StudentNotFoundException;
import com.student.api.model.Student;
import com.student.api.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

  private final StudentRepository repository;

  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "/students", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  List<Student> all() {
    return repository.findAll();
  }


  @PostMapping("/students")
  ResponseEntity<?> newStudent(@RequestBody Student newStudent) {

    Student saved = repository.save(newStudent);

    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @Operation(summary = "Get a student by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the student",
              content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = Student.class)) }),
      @ApiResponse(responseCode = "404", description = "Student not found",
          content = @Content) })
  @GetMapping("/students/{id}")
  Student one(@Parameter(name = "id", description = "The student id") @PathVariable Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
  }

  @PutMapping("/students/{id}")
  Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
    return repository.findById(id)
            .map(s -> {
              s.setFirstName(newStudent.getFirstName());
              s.setLastName(newStudent.getLastName());
              s.setBirthDate(newStudent.getBirthDate());
              return repository.save(s);
            })
            .orElseGet(() -> {
              newStudent.setId(id);
              return repository.save(newStudent);
            });
  }

  @DeleteMapping("/students/{id}")
  ResponseEntity<?> deleteStudent(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
