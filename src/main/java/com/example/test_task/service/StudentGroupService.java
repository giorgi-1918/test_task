package com.example.test_task.service;


import com.example.test_task.helper.RequestRLO;


import com.example.test_task.model.*;
import com.example.test_task.repository.GroupRepository;

import com.example.test_task.repository.StGrRepository;
import com.example.test_task.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupService implements StudentGroupServiceI{
    @Autowired
    private StGrRepository stGrRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;


    private Group getGroup(RequestRLO request){
        Optional<Group> optional_gr = groupRepository.findByNumber(request.getGroup_num());
        if(!optional_gr.isPresent()) return null;
        return optional_gr.get();
    }

    private Student getStudent(RequestRLO request){
        String private_id = request.getPrivate_id();
        Optional<Student> optional_st = studentRepository.findByPrivate_id(private_id);
        if(!optional_st.isPresent()) return null;
        return optional_st.get();
    }



    @Override
    public String save(RequestRLO request) {
        Group group = getGroup(request);
        if(group==null) return "groupNotFound";
        Student student = getStudent(request);
        if(student == null) return  "studentNotFound";
        List<Student_Group> s_g = stGrRepository.findByStudentAndGroup(group,student);
        if(!s_g.isEmpty()) return "alreadyExist";
        Student_Group student_group = new Student_Group();
        student_group.setGroup(group);
        student_group.setStudent(student);
        stGrRepository.save(student_group);
        return "addToGroup";
    }

    @Override
    public List<Student_Group> find(Integer number) {
        Optional<Group> optional = groupRepository.findByNumber(number);
        if(!optional.isPresent()) throw new RuntimeException("Group cannot be found");
        Group group = optional.get();
        List<Student_Group> st_gr =  stGrRepository.findByGroup(group);
        return st_gr;
    }

    public String delete(RequestRLO request){
        Group group = getGroup(request);
        if(group==null) return "groupNotFound";
        Student student = getStudent(request);
        if(student == null) return  "studentNotFound";
        List<Student_Group> s_g = stGrRepository.findByStudentAndGroup(group,student);
        if(s_g.isEmpty())  return "notMember";
        stGrRepository.delete(s_g.get(0));
        return "groupLeft";
    }

    @Override
    public List<Student_Group> getAll(){
        return stGrRepository.findAll();
    }

    @Override
    public void deleteByGroup(Group group) {
        stGrRepository.deleteByGroup(group);
    }
}
