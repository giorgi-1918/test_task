package com.example.test_task.controller;


import com.example.test_task.helper.RequestSearchO;
import com.example.test_task.model.Teacher;
import com.example.test_task.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/viewAddTeacher")
    public String viewTeacherForm(Model model) {
        model.addAttribute("teach", new Teacher());
        return "createTeacher";
    }

    @PostMapping("/addTeacher")
    public String saveTeacher(@ModelAttribute("teach") Teacher teacher) {
        if (teacher.getDate() == null || teacher.getName().equals("") || teacher.getLast_name().equals("") || teacher.getPrivate_id().equals("") || teacher.getMail().equals("")) {
            return "failedCreation";
        }
        boolean indicator = teacherService.save(teacher);
        if (!indicator) return "mailOrPrivate_NumReserved";
        return "successfulCreation";
    }

    @GetMapping("/viewFindTeacher")
    public String viewSearchingTeacher(@ModelAttribute("requestSearchO") RequestSearchO r_s, Model model) {

        if (r_s.getDate() == null || r_s.getName().equals("") || r_s.getLast_name().equals("") || r_s.getPrivate_id().equals("")) {
            return "emptyField";
        }
        Teacher teacher = teacherService.find(r_s);
        if(teacher==null) return "teacherNotFound";
        model.addAttribute("teacher", teacher);
        return "teacherData";
    }

    @GetMapping("/viewUpdateForm/{id}")
    public String viewUpdateForm(@PathVariable(value = "id") Integer id, Model model) {
        Teacher teacher = teacherService.findByID(id);
        model.addAttribute("teacher", teacher);
        return "updateTeacher";
    }

    @GetMapping("/Delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        teacherService.delete(id);
        return "deleteMessage";
    }


}