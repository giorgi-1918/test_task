package com.example.test_task.repository;


import com.example.test_task.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "SELECT s FROM Student s where s.name = ?1 and s.last_name = ?2 and s.private_id = ?3 and s.date = ?4")
    Optional<Student> findByParameters(String name, String last_name, String private_id, Date date);

    @Query(value = "SELECT s FROM Student s where s.private_id = ?1")
    Optional<Student> findByPrivate_id(String private_id);

    Optional<Student> findByMail(String mail);


}