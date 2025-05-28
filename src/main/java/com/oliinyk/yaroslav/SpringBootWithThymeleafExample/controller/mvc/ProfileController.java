package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.AddressDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ProfileDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("profileControllerBean")
public class ProfileController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model, HttpSession session) {
        PersonDto personDto = (PersonDto) session.getAttribute("loggedInPerson");
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(personDto.getName());
        profileDto.setMobileNumber(personDto.getMobileNumber());
        profileDto.setEmail(personDto.getEmail());
        if(personDto.getAddress() != null && personDto.getAddress().getAddressId() > 0) {
            profileDto.setAddress1(personDto.getAddress().getAddress1());
            profileDto.setAddress2(personDto.getAddress().getAddress2());
            profileDto.setCity(personDto.getAddress().getCity());
            profileDto.setState(personDto.getAddress().getState());
            profileDto.setZipCode(personDto.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profileDto);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(
        @Valid @ModelAttribute("profile") ProfileDto profile,
        Errors errors,
        HttpSession session
    ) {
        if(errors.hasErrors()) {
            return "profile.html";
        }
        PersonDto personDto = (PersonDto) session.getAttribute("loggedInPerson");
        personDto.setName(profile.getName());
        personDto.setEmail(profile.getEmail());
        personDto.setMobileNumber(profile.getMobileNumber());
        if(personDto.getAddress() ==null || !(personDto.getAddress().getAddressId()>0)){
            personDto.setAddress(new AddressDto());
        }
        personDto.getAddress().setAddress1(profile.getAddress1());
        personDto.getAddress().setAddress2(profile.getAddress2());
        personDto.getAddress().setCity(profile.getCity());
        personDto.getAddress().setState(profile.getState());
        personDto.getAddress().setZipCode(profile.getZipCode());
        PersonDto savedPersonDto = personService.save(personDto);
        session.setAttribute("loggedInPerson", savedPersonDto);
        return "redirect:/displayProfile";
    }
}
