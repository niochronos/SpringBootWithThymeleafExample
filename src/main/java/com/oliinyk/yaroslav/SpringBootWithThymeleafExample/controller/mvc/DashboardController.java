package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.PersonService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    private PersonService personService;

    @Value("${app-property.pageSize}")
    private int defaultPageSize;

    @Value("${app-property.contact.successMsg}")
    private String message;

    @Autowired
    Environment environment;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Optional<PersonDto> person = personService.readByEmail(authentication.getName());
        person.ifPresent(personDto -> {
            model.addAttribute("username", personDto.getName());
            model.addAttribute("roles", authentication.getAuthorities().toString());
            if(personDto.getSchoolClass() != null && personDto.getSchoolClass().getName() != null) {
                model.addAttribute("enrolledClass", personDto.getSchoolClass().getName());
            }
            session.setAttribute("loggedInPerson", personDto);
        });
//        logMessages();
        return "dashboard.html";
    }

    private void logMessages() {
        log.error("Error message from the Dashboard page");
        log.warn("Warning message from the Dashboard page");
        log.info("Info message from the Dashboard page");
        log.debug("Debug message from the Dashboard page");
        log.trace("Trace message from the Dashboard page");

        log.error("defaultPageSize value with @Value annotation is : {}", defaultPageSize);
        log.error("successMsg value with @Value annotation is : {}", message);

        log.error("defaultPageSize value with Environment is : {}", environment.getProperty("app-property.pageSize"));
        log.error("successMsg value with Environment is : {}", environment.getProperty("app-property.contact.successMsg"));
        log.error("Java Home environment variable using Environment is : {}", environment.getProperty("JAVA_HOME"));
    }
}
