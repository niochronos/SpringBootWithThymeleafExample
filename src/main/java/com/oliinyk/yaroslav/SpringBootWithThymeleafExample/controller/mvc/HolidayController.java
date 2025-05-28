package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.mvc;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.HolidayDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.HolidayService;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.type.HolidayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {
        if(null != display && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("federal")){
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("festival")){
            model.addAttribute("festival",true);
        }
        List<HolidayDto> holidayList = holidayService.findAll();
        for (HolidayType type : HolidayType.values()) {
            model.addAttribute(type.toString(),
                (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
