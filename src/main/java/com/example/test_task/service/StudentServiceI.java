package com.example.test_task.service;

import com.example.test_task.helper.RequestSearchO;
import com.example.test_task.model.Student;

public interface StudentServiceI {
    boolean save(Student student);
    Student find(RequestSearchO request);
    void delete(int id);

    Student findByID(Integer id);
}
