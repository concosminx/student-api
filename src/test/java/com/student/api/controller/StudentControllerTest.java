package com.student.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.api.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;


  //@WithMockUser(value = "admin")
  @Test
  public void shouldReturn200AndSpecificRecords() throws Exception {
    this.mockMvc.perform(
                    get("/students/v1")
                            .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Chloe")));
  }

  @Test
  public void shouldReturnStatus400ForPageLessThanZero() throws Exception {
    this.mockMvc.perform(
                    get("/students/v1")
                            .param("page", "-1")
                            .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isBadRequest());
  }

  //@WithMockUser(value = "admin")
  @Test
  public void shouldReturnStatus200AndSpecificStudent() throws Exception {
    mockMvc.perform(
                    get("/students/v1/{id}", 1L)
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Jack"))
            .andExpect(jsonPath("$.lastName").value("Bauer"));
  }


  @Test
  void whenStudentIsValid_thenReturnsStatus200() throws Exception {
    StudentDto studentDto = validStudent();
    String body = objectMapper.writeValueAsString(studentDto);

    mockMvc.perform(post("/students/v1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(body))
            .andExpect(status().isCreated());
  }

  @Test
  void whenInputIsInvalid_thenReturnsStatus400WithErrorObject() throws Exception {
    StudentDto studentDto = invalidStudent();
    String body = objectMapper.writeValueAsString(studentDto);

    MvcResult result = mockMvc.perform(post("/students/v1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(body))
            .andExpect(status().isBadRequest())
            .andReturn();

    assertThat(result.getResponse().getContentAsString()).contains("violations");
  }

  private StudentDto validStudent() {
    StudentDto s = new StudentDto();
    s.setFirstName("John");
    s.setLastName("Doe");
    s.setBirthDate(LocalDate.of(1990, 1, 1));
    return s;
  }

  private StudentDto invalidStudent() {
    StudentDto s = new StudentDto();
    s.setFirstName("John");
    return s;
  }

}
