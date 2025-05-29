package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.CourseDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.SchoolClassDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.CourseService;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.PersonService;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.SchoolClassService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    SchoolClassService schoolClassService;

    @Autowired
    PersonService personService;

    @Autowired
    CourseService courseService;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClassDto> schoolClasses = schoolClassService.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("schoolClasses", schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClassDto());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(
        Model model,
        @ModelAttribute("schoolClass") SchoolClassDto schoolClass
    ) {
        schoolClassService.save(schoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<SchoolClassDto> schoolClassOpt = schoolClassService.findById(id);
        schoolClassOpt.ifPresent(schoolClassDto ->
            schoolClassDto.getPersons().forEach(
                personDto -> {
                    personDto.setSchoolClass(null);
                    personService.save(personDto);
                }
            )
        );
        schoolClassService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(
        Model model,
        @RequestParam int classId,
        HttpSession session,
        @RequestParam(value = "error", required = false) String error
    ) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<SchoolClassDto> schoolClassOpt = schoolClassService.findById(classId);
        modelAndView.addObject("person", new PersonDto());
        schoolClassOpt.ifPresent( schoolClassDto -> {
            modelAndView.addObject("schoolClass", schoolClassDto);
            session.setAttribute("schoolClass", schoolClassDto);
        });
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(
        Model model,
        @ModelAttribute("person") PersonDto person,
        HttpSession session
    ) {
        ModelAndView modelAndView = new ModelAndView();
        SchoolClassDto schoolClassDto = (SchoolClassDto) session.getAttribute("schoolClass");
        Optional<PersonDto> personOpt = personService.readByEmail(person.getEmail());
        if(personOpt.isEmpty() || !(personOpt.get().getPersonId() > 0)) {
            modelAndView.setViewName(
                "redirect:/admin/displayStudents?classId=" + schoolClassDto.getClassId() + "&error=true"
            );
            return modelAndView;
        }
        personOpt.ifPresent(personDto -> {
            personDto.setSchoolClass(schoolClassDto);
            personService.save(personDto);
            schoolClassDto.getPersons().add(personDto);
        });
        schoolClassService.save(schoolClassDto);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + schoolClassDto.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(
        Model model,
        @RequestParam int personId,
        HttpSession session
    ) {
        SchoolClassDto schoolClassDto = (SchoolClassDto) session.getAttribute("schoolClass");
        Optional<PersonDto> personOpt = personService.findById(personId);
        personOpt.ifPresent(
            personDto -> {
                personDto.setSchoolClass(null);
                schoolClassDto.getPersons().remove(personDto);
            }
        );
        SchoolClassDto schoolClassDtoSaved = schoolClassService.save(schoolClassDto);
        session.setAttribute("schoolClass", schoolClassDtoSaved);
        ModelAndView modelAndView = new ModelAndView(
            "redirect:/admin/displayStudents?classId=" + schoolClassDto.getClassId()
        );
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        //List<Courses> courses = coursesRepository.findByOrderByNameDesc();
        List<CourseDto> courses = courseService.findAll(Sort.by("name").descending());
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new CourseDto());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(
        Model model,
        @ModelAttribute("course") CourseDto course
    ) {
        ModelAndView modelAndView = new ModelAndView();
        courseService.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(
        Model model,
        @RequestParam int id,
        HttpSession session,
        @RequestParam(required = false) String error
    ) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<CourseDto> courseOpt = courseService.findById(id);
        modelAndView.addObject("person", new PersonDto());
        courseOpt.ifPresent(courseDto -> {
            modelAndView.addObject("course", courseDto);
            session.setAttribute("course", courseDto);
        });
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(
        Model model,
        @ModelAttribute("person") PersonDto person,
        HttpSession session
    ) {
        ModelAndView modelAndView = new ModelAndView();
        CourseDto courseDto = (CourseDto) session.getAttribute("course");
        Optional<PersonDto> personOpt = personService.readByEmail(person.getEmail());
        if(personOpt.isEmpty() || !(personOpt.get().getPersonId() > 0)) {
            modelAndView.setViewName(
                "redirect:/admin/viewStudents?id=" + courseDto.getCourseId() + "&error=true"
            );
            return modelAndView;
        }
        personOpt.ifPresent(personDto -> {
            personDto.getCourses().add(courseDto);
            courseDto.getPersons().add(personDto);
            personService.save(personDto);
        });
        session.setAttribute("course", courseDto);
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courseDto.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(
        Model model,
        @RequestParam int personId,
        HttpSession session
    ) {
        CourseDto courseDto = (CourseDto) session.getAttribute("course");
        Optional<PersonDto> personOpt = personService.findById(personId);
        personOpt.ifPresent(personDto -> {
            personDto.getCourses().remove(courseDto);
            courseDto.getPersons().remove(personDto);
            personService.save(personDto);
        });
        session.setAttribute("course", courseDto);
        ModelAndView modelAndView = new ModelAndView(
            "redirect:/admin/viewStudents?id=" + courseDto.getCourseId()
        );
        return modelAndView;
    }
}
