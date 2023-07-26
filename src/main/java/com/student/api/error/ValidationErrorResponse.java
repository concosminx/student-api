package com.student.api.error;


import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

  private List<Violation> violations = new ArrayList<>();

  public List<Violation> getViolations() {
    return violations;
  }

  public void setViolations(List<Violation> violations) {
    this.violations = violations;
  }


  public static class Violation {

    private final String fieldName;

    private final String message;

    public Violation(String fieldName, String message) {
      this.fieldName = fieldName;
      this.message = message;
    }

    public String getFieldName() {
      return fieldName;
    }

    public String getMessage() {
      return message;
    }
  }

}
