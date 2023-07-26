package com.student.api.controller;

import com.student.api.dto.StudentDto;
import com.student.api.hateoas.StudentModelAssembler;
import com.student.api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/students/v2")
public class StudentControllerHateoas {

  //see https://spring.io/guides/tutorials/rest/

  private final StudentService studentService;
  private final StudentModelAssembler studentModelAssembler;

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
  public CollectionModel<EntityModel<StudentDto>> all(
          @Parameter(name = "page", description = "Page number, indexed from 1")
          @RequestParam(value = "page", defaultValue = "0") int page,
          @Parameter(name = "limit", description = "Page size")
          @RequestParam(value = "limit", defaultValue = "4") int limit
  ) {
    List<StudentDto> students = studentService.getStudents(page, limit);
    List<EntityModel<StudentDto>> entityModelList = students.stream().map(studentModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(entityModelList, linkTo(methodOn(StudentControllerHateoas.class).all(0, 10)).withSelfRel());
  }


  @Operation(summary = "Creates a new student")
  @PostMapping(
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
  )
  ResponseEntity<?> newStudent(@Parameter(description = "Student details", schema = @Schema(implementation = StudentDto.class))
                               @RequestBody(required = true)
                               StudentDto studentDetails) {
    StudentDto student = studentService.createStudent(studentDetails);
    EntityModel<StudentDto> studentDtoEntityModel = studentModelAssembler.toModel(student);
    return ResponseEntity.created(studentDtoEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(studentDtoEntityModel);
  }

  @Operation(summary = "Get a student by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the student"),
          @ApiResponse(responseCode = "404", description = "Student not found",
                  content = @Content)})
  @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public EntityModel<StudentDto> one(@Parameter(name = "id", description = "The student id") @PathVariable Long id) {
    StudentDto studentById = studentService.findStudentById(id);
    return studentModelAssembler.toModel(studentById);
  }

  @Operation(summary = "Updates an existing student")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found and updated the student"),
          @ApiResponse(responseCode = "404", description = "Student not found",
                  content = @Content)})
  @PutMapping("/{id}")
  ResponseEntity<?> updateStudent(@RequestBody StudentDto newStudent,
                                  @PathVariable Long id) {
    StudentDto studentDto = studentService.updateStudent(newStudent, id);
    EntityModel<StudentDto> studentDtoEntityModel = studentModelAssembler.toModel(studentDto);

    return ResponseEntity.ok(studentDtoEntityModel);
  }

  @Operation(summary = "Deletes a student based on id")
  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

}
