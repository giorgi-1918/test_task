package com.example.test_task.repository;





import com.example.test_task.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "SELECT t FROM Teacher t where t.name = ?1 and t.last_name = ?2 and t.private_id = ?3 and t.date = ?4")
    Optional<Teacher> findByParameters(String name, String last_name, String private_id, Date date);
    @Query(value = "SELECT t FROM Teacher t where t.private_id = ?1")
    Optional<Teacher> findByPrivate_id(String private_id);
    Optional<Teacher> findByMail(String mail);
}