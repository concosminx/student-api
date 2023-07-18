package com.student.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
}
