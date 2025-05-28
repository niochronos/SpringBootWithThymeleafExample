package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("public")
public class PublicController {

    @Autowired
    private PersonService personService;

    @GetMapping(value ="/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new PersonDto());
        return "register.html";
    }

    @PostMapping(value ="/createUser")
    public String createUser(@Valid @ModelAttribute("person") PersonDto person, Errors errors) {
        if(errors.hasErrors()) {
            return "register.html";
        }
        boolean isSaved = personService.createNewPerson(person);
        if(isSaved) {
            return "redirect:/login?register=true";
        }else {
            return "register.html";
        }
    }
}
