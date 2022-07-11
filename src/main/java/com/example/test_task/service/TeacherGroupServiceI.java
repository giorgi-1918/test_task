package com.example.test_task.service;

import com.example.test_task.helper.RequestRLO;


import com.example.test_task.model.Group;
import com.example.test_task.model.Teacher_Group;

import java.util.List;

public interface TeacherGroupServiceI {


    String save(RequestRLO request);

    List<Teacher_Group> find(Integer number);

    String delete(RequestRLO request);

    List<Teacher_Group> getAll();

    void deleteByGroup(Group group);
}
