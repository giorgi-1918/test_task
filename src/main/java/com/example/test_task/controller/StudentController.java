package com.example.test_task.controller;

import com.example.test_task.helper.RequestSearchO;
import com.example.test_task.model.Student;
import com.example.test_task.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/viewAddStudent")
    public String viewStudentForm(Model model){
        model.addAttribute("stud", new Student());
        return "createStudent";
    }

    private boolean check(Student student){
        return student.getDate() == null || student.getName()=="" || student.getLast_name()=="" || student.getPrivate_id()=="" || student.getMail()=="";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@ModelAttribute ("stud") Student student){
        if(check(student)){
          return "failedCreation";
        }
        boolean indicator = studentService.save(student);
        if(!indicator) return "mailOrPrivate_NumReserved";
        return "successfulCreation";
    }

    @GetMapping("/viewFindStudent")
    public String viewSearchingStudent(@ModelAttribute ("requestSearchO") RequestSearchO r_s, Model model){

        if(r_s.getDate() == null || r_s.getName().equals("") || r_s.getLast_name().equals("") || r_s.getPrivate_id().equals("")){
            return "emptyField";
        }
        Student student = studentService.find(r_s);
        if(student==null) return "studentNotFound";
        model.addAttribute("student", student);
        return "studentData";
    }

    @GetMapping("/viewUpdateForm/{id}")
    public String viewUpdateForm(@PathVariable(value="id") Integer id, Model model){
        Student student= studentService.findByID(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @GetMapping("/Delete/{id}")
    public String delete(@PathVariable(value="id") Integer id){
        studentService.delete(id);
        return "deleteMessage";
    }


}
