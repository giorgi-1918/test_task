package com.example.test_task.repository;


import com.example.test_task.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StGrRepository extends JpaRepository<Student_Group, Integer> {

    @Query("SELECT s_g FROM Student_Group s_g where s_g.group=?1 and s_g.student=?2")
    List<Student_Group> findByStudentAndGroup(Group group, Student student);


    void deleteByStudent(Student student);

    void deleteByGroup(Group group);

    List<Student_Group> findByGroup(Group group);


}

