package com.example.test_task.repository;


import com.example.test_task.model.Group;

import com.example.test_task.model.Teacher;
import com.example.test_task.model.Teacher_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeGrRepository extends JpaRepository<Teacher_Group, Integer> {


    @Query("SELECT t_g FROM Teacher_Group t_g where t_g.group=?1 and t_g.teacher=?2")
    List<Teacher_Group> findByTeacherAndGroup(Group group, Teacher teacher);

    void deleteByTeacher(Teacher teacher);

    void deleteByGroup(Group group);

    List<Teacher_Group> findByGroup(Group group);
}

