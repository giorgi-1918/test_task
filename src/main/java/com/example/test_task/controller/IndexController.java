package com.example.test_task.controller;



import com.example.test_task.helper.RequestNumber;
import com.example.test_task.helper.RequestRLO;
import com.example.test_task.helper.RequestSearchO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/student")
    public String viewStudentPage(Model model){
        model.addAttribute("requestSearchO", new RequestSearchO());
        return "student";
    }

    @GetMapping("/teacher")
    public String viewTeacherPage(Model model){
        model.addAttribute("requestSearchO", new RequestSearchO());
        return "teacher";
    }

    @GetMapping("/group")
    public String viewGroupPage(Model model){
        model.addAttribute("RLForm", new RequestRLO());
        model.addAttribute("numberWrapper", new RequestNumber());
        return "group";
    }
}
