package com.example.test_task.service;

import com.example.test_task.helper.RequestRLO;


import com.example.test_task.model.Group;
import com.example.test_task.model.Student_Group;

import java.util.List;

public interface StudentGroupServiceI {


    String save(RequestRLO request);

    List<Student_Group> find(Integer number);

    String delete(RequestRLO request);

    List<Student_Group> getAll();

    void deleteByGroup(Group group);
}
