package com.student.api.rest;

import com.student.api.exceptions.StudentNotFoundException;
import com.student.api.model.Student;
import com.student.api.repository.StudentRepository;
import org.springframework.http.MediaType;
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
  Student newStudent(@RequestBody Student newStudent) {
    return repository.save(newStudent);
  }

  @GetMapping("/students/{id}")
  Student one(@PathVariable Long id) {
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
  void deleteStudent(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
