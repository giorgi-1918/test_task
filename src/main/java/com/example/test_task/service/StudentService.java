package com.example.test_task.service;

import com.example.test_task.helper.RequestSearchO;
import com.example.test_task.model.Student;
import com.example.test_task.repository.StGrRepository;
import com.example.test_task.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceI{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StGrRepository stGrRepository;


    @Override
    public boolean save(Student student) {
        Optional<Student> optional_1 = studentRepository.findByMail(student.getMail());
        Optional<Student> optional_2 = studentRepository.findByPrivate_id(student.getPrivate_id());
        if((!optional_1.isEmpty() && optional_1.get().getId()!= student.getId()) || (!optional_2.isEmpty() && optional_2.get().getId()!= student.getId()) ){
            return false;
        }
        studentRepository.save(student);
        return true;
    }

    @Override
    public Student find(RequestSearchO request) {
        Optional<Student> optional = studentRepository.findByParameters(request.getName(), request.getLast_name(),request.getPrivate_id(),request.getDate());
        if(optional.isEmpty()) return null;
        return optional.get();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Optional<Student> optional = studentRepository.findById(id);
        stGrRepository.deleteByStudent(optional.get());
        studentRepository.deleteById(id);
    }
    @Override
    public Student findByID(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        return optional.get();
    }
}
