package com.example.test_task.controller;


import com.example.test_task.helper.RequestNumber;
import com.example.test_task.helper.RequestRLO;
import com.example.test_task.model.Group;
import com.example.test_task.service.GroupService;
import com.example.test_task.service.StudentGroupService;
import com.example.test_task.service.TeacherGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentGroupService studentGroupService;
    @Autowired
    private TeacherGroupService teacherGroupService;


    @GetMapping("/viewAddGroup")
    public String viewGroupForm(Model model){
        model.addAttribute("group", new Group());
        return "createGroup";
    }



    @PostMapping("/addGroup")
    public String saveGroup(@ModelAttribute("group") Group group){
        if(group.getName().equals("") || group.getNumber()==null){
            return "failedCreation";
        }
        boolean indicator = groupService.save(group);
        if(!indicator) return "numberIsReserved";
        return "successfulCreation";
    }

    @GetMapping("/viewFindGroup")
    public String viewSearchingGroup(@ModelAttribute("numberWrapper") RequestNumber request, Model model){

        if(request.getNumber()==null){
            return "emptyField";
        }
        List<Group> groupList = groupService.find(request.getNumber());
        if(groupList.isEmpty()) return "groupNotFound";
        model.addAttribute("groupList", groupList);
        return "groupData";
    }

    @GetMapping("/viewUpdateForm/{id}")
    public String viewUpdateForm(@PathVariable(value="id") Integer id, Model model){
        Group group = groupService.findByID(id);
        model.addAttribute("group", group);
        return "updateGroup";
    }

    @GetMapping("/Delete/{id}")
    public String delete(@PathVariable(value="id") Integer id){
        groupService.delete(id);
        return "deleteMessage";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("groupList", groupService.getAll());
        return "groupData";
    }

    @PostMapping("/Register_Student")
    public String Register_Student(@ModelAttribute("RLForm") RequestRLO request){
        if(request.getGroup_num()==null || request.getPrivate_id().equals("")) return "emptyField";
        return studentGroupService.save(request);
    }

    @PostMapping("/Register_Teacher")
    public String Register_Teacher(@ModelAttribute("RLForm") RequestRLO request){
        if(request.getGroup_num()==null || request.getPrivate_id().equals("")) return "emptyField";
        return teacherGroupService.save(request);
    }

    @PostMapping("/Student_Left")
    public String Student_Left(@ModelAttribute("RLForm") RequestRLO request){
        if(request.getGroup_num()==null || request.getPrivate_id().equals("")) return "emptyField";
        return studentGroupService.delete(request);
    }

    @PostMapping("/Teacher_Left")
    public String Teacher_Left(@ModelAttribute("RLForm") RequestRLO request){
        if(request.getGroup_num()==null || request.getPrivate_id().equals("")) return "emptyField";
        return teacherGroupService.delete(request);
    }

    @GetMapping("/getAllStudent_Group")
    public String getAllStudent_Group(Model model){
        model.addAttribute("list", studentGroupService.getAll());
        return "studentGroupData";
    }

    @GetMapping("/getAllTeacher_Group")
    public String getAllTeacher_Group(Model model){
        model.addAttribute("list", teacherGroupService.getAll());
        return "teacherGroupData";
    }




}
