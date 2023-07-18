package com.student.api.init;

import com.student.api.entity.Student;
import com.student.api.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
public class InitDatabase {

  @Bean
  public CommandLineRunner initDataBase(StudentRepository repository) {
    return (args) -> {
      if (repository.findAll().isEmpty()) {
        log.info("Empty DB; Let's add some records:");

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
      }
    };
  }
}
