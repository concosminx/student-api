package com.student.api.configuration;

import com.student.api.ApiApplication;
import com.student.api.model.Student;
import com.student.api.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);

  @Bean
  public CommandLineRunner initDataBase(StudentRepository repository) {
    return (args) -> {

      repository.save(new Student("Jack", "Bauer", LocalDate.of(2004, 12, 9)));
      repository.save(new Student("Chloe", "O'Brian", LocalDate.of(2004, 6, 9)));
      repository.save(new Student("Kim", "Bauer", LocalDate.of(2004, 8, 15)));
      repository.save(new Student("David", "Palmer", LocalDate.of(2004, 1, 31)));
      repository.save(new Student("Michelle", "Dessler", LocalDate.of(2004, 7, 7)));

      log.info("Students found with findAll():");
      log.info("-------------------------------");
      for (Student s : repository.findAll()) {
        log.info(s.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Student student = repository.findById(1L).get();
      log.info("Student found with findById(1L):");
      log.info("--------------------------------");
      log.info(student.toString());
      log.info("");

      // fetch customers by last name
      log.info("Student found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      repository.findByLastName("Bauer").forEach(bauer -> {
        log.info(bauer.toString());
      });
      log.info("");
    };
  }
}
