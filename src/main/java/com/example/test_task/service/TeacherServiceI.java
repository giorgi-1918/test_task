package com.example.test_task.service;

import com.example.test_task.helper.RequestSearchO;

import com.example.test_task.model.Teacher;

public interface TeacherServiceI {
    boolean save(Teacher teacher);
    Teacher find(RequestSearchO request);
    void delete(int id);

    Teacher findByID(Integer id);
}
