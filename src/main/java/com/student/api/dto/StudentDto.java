package com.student.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotNull
  private LocalDate birthDate;
}
