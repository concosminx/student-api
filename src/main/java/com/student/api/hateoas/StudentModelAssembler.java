package com.student.api.hateoas;

import com.student.api.controller.StudentControllerHateoas;
import com.student.api.dto.StudentDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<StudentDto, EntityModel<StudentDto>> {
  @Override
  public EntityModel<StudentDto> toModel(StudentDto entity) {
    return EntityModel.of(entity, //
            linkTo(methodOn(StudentControllerHateoas.class).one(entity.getId())).withSelfRel(),
            linkTo(methodOn(StudentControllerHateoas.class).all(0, 10)).withRel("students"));
  }
}
