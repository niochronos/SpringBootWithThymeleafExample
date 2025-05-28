package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session) {
        PersonDto personDto = (PersonDto) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", personDto);
        return modelAndView;
    }
}
