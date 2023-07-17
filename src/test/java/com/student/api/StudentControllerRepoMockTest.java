package com.student.api;

import com.student.api.model.Student;
import com.student.api.repository.StudentRepository;
import com.student.api.rest.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerRepoMockTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentRepository repository;

  @WithMockUser(value = "admin")
  @Test
  public void greetingShouldReturnMessageFromService() throws Exception {
    when(repository.findAll()).thenReturn(List.of(new Student("Ana", "Popescu", LocalDate.of(2004, 12,12))));
    this.mockMvc.perform(get("/students")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Popescu")));
  }
}
