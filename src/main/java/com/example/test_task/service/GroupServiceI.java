package com.example.test_task.service;

import com.example.test_task.model.Group;

import java.util.List;

public interface GroupServiceI {
    boolean save(Group group);
    List<Group> find(Integer number);
    void delete(Integer number);
    Group findByID(Integer id);
}
