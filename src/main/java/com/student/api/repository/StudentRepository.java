package com.student.api.repository;

import com.student.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
  List<Student> findByLastName(String lastName);
  List<Student> findByFirstName(String firstName);
}
