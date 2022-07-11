package com.example.test_task.service;

import com.example.test_task.model.Group;
import com.example.test_task.model.Student;
import com.example.test_task.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements GroupServiceI{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private List<Group> groupList;
    @Autowired
    private TeacherGroupService teacherGroupService;
    @Autowired
    private StudentGroupService studentGroupService;


    @Override
    public boolean save(Group group) {
        Optional<Group> optional = groupRepository.findByNumber(group.getNumber());
        if(!optional.isEmpty() && optional.get().getId()!= group.getId() ){
            return false;
        }
        groupRepository.save(group);
        return true;
    }

    @Override
    public List<Group> find(Integer number) {
        groupList.clear();
        Optional<Group> optional = groupRepository.findByNumber(number);
        if(optional.isEmpty()){
           return groupList;
        }
        Group group = optional.get();
        groupList.add(group);
        return groupList;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Optional<Group> optional=groupRepository.findById(id);
        teacherGroupService.deleteByGroup(optional.get());
        studentGroupService.deleteByGroup(optional.get());
        groupRepository.deleteById(id);
    }

    public Group findByID(Integer id) {
        Optional<Group> optional = groupRepository.findById(id);
        return optional.get();
    }

    public List<Group> getAll(){
        return groupRepository.findAll();
    }
}
