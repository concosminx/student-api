package com.student.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  //@WithMockUser(value = "admin")
  @Test
  public void shouldReturnSomeRecords() throws Exception {
    this.mockMvc.perform(
                    get("/students")
                            .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Chloe")));
  }

  //@WithMockUser(value = "admin")
  @Test
  public void getFirstStudentTest() throws Exception {
    mockMvc.perform(
                    get("/students/{id}", 1L)
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Jack"))
            .andExpect(jsonPath("$.lastName").value("Bauer"));
  }


}
