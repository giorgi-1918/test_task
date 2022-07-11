package com.example.test_task.service;

import com.example.test_task.helper.RequestSearchO;


import com.example.test_task.model.Teacher;
import com.example.test_task.repository.TeGrRepository;
import com.example.test_task.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TeacherService implements TeacherServiceI{
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeGrRepository teGrRepository;


    @Override
    public boolean save(Teacher teacher) {
        Optional<Teacher> optional_1 = teacherRepository.findByMail(teacher.getMail());
        Optional<Teacher> optional_2 = teacherRepository.findByPrivate_id(teacher.getPrivate_id());
        if((!optional_1.isEmpty() && optional_1.get().getId()!= teacher.getId()) || (!optional_2.isEmpty() && optional_2.get().getId()!= teacher.getId()) ){
            return false;
        }
        teacherRepository.save(teacher);
        return true;
    }

    @Override
    public Teacher find(RequestSearchO request) {
        Optional<Teacher> optional = teacherRepository.findByParameters(request.getName(), request.getLast_name(),request.getPrivate_id(),request.getDate());
        if(optional.isEmpty()) return null;
        return optional.get();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        teGrRepository.deleteByTeacher(optional.get());
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher findByID(Integer id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        return optional.get();
    }
}
