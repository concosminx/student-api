package com.student.api.repository;

import com.student.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findByLastName(String lastName);



}
