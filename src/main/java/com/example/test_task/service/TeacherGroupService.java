package com.example.test_task.service;



import com.example.test_task.helper.RequestRLO;
import com.example.test_task.model.*;
import com.example.test_task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherGroupService implements TeacherGroupServiceI{
    @Autowired
    private TeGrRepository teGrRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    private Group getGroup(RequestRLO request){
        Optional<Group> optional_gr = groupRepository.findByNumber(request.getGroup_num());
        if(!optional_gr.isPresent()) return null;
        return optional_gr.get();
    }

    private Teacher getTeacher(RequestRLO request){
        String private_id = request.getPrivate_id();
        Optional<Teacher> optional_te = teacherRepository.findByPrivate_id(private_id);
        if(!optional_te.isPresent()) return null;
        return optional_te.get();
    }

    @Override
    public String save(RequestRLO request) {
        Group group = getGroup(request);
        if(group==null) return "groupNotFound";
        Teacher teacher = getTeacher(request);
        if(teacher == null) return  "teacherNotFound";
        List<Teacher_Group> t_g = teGrRepository.findByTeacherAndGroup(group,teacher);
        if(t_g.isEmpty())  return "alreadyExist";
        Teacher_Group teacher_group = new Teacher_Group();
        teacher_group.setGroup(group);
        teacher_group.setTeacher(teacher);
        teGrRepository.save(teacher_group);
        return "addToGroup";
    }

    @Override
    public List<Teacher_Group> find(Integer number) {
        Optional<Group> optional = groupRepository.findByNumber(number);
        if(!optional.isPresent()) throw new RuntimeException("Group cannot be found");
        Group group = optional.get();
        List<Teacher_Group> st_gr =  teGrRepository.findByGroup(group);
        return st_gr;
    }

    @Override
    public String delete(RequestRLO request){
        Group group = getGroup(request);
        if(group==null) return "groupNotFound";
        Teacher teacher = getTeacher(request);
        if(teacher == null) return  "teacherNotFound";
        List<Teacher_Group> t_g = teGrRepository.findByTeacherAndGroup(group,teacher);
        if(t_g.isEmpty())  return "notMember";
        teGrRepository.delete(t_g.get(0));
        return "groupLeft";
    }
    @Override
    public List<Teacher_Group> getAll(){
        return teGrRepository.findAll();
    }

    public void deleteByGroup(Group group) {
        teGrRepository.deleteByGroup(group);
    }
}
