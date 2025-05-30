package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.controller.rest;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.constant.AppConstants;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ContactDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ResponseDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(
    path = "/api/contact",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public class ContactRestController {

    @Autowired
    ContactService contactService;

    @GetMapping("/getMessagesByStatus")
    //@ResponseBody
    public List<ContactDto> getMessagesByStatus(@RequestParam(name = "status")  String status) {
        return contactService.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    //@ResponseBody
    public List<ContactDto> getAllMsgsByStatus(@RequestBody ContactDto contactDto) {
        if(null != contactDto && null != contactDto.getStatus()) {
            return contactService.findByStatus(contactDto.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    // @ResponseBody
    public ResponseEntity<ResponseDto> saveMsg(
        @RequestHeader("invocationFrom") String invocationFrom,
        @Valid @RequestBody ContactDto contactDto
    ) {
        log.info("Header invocationFrom = {}", invocationFrom);
        contactService.save(contactDto);
        ResponseDto response = new ResponseDto();
        response.setStatusCode("201");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("isMsgSaved", "true")
            .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<ResponseDto> deleteMsg(RequestEntity<ContactDto> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
                log.info("Header '{}' = {}", key, value.stream().collect(Collectors.joining("|")));
            }
        );
        ContactDto contactDto = requestEntity.getBody();
        contactService.deleteById(contactDto.getContactId());
        ResponseDto response = new ResponseDto();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<ResponseDto> closeMsg(@RequestBody ContactDto contactDto) {
        ResponseDto response = new ResponseDto();
        Optional<ContactDto> contact = contactService.findById(contactDto.getContactId());
        if (contact.isPresent() && contact.get().getStatus().equals(AppConstants.CLOSE)) {
            response.setStatusCode("400");
            response.setStatusMsg("Message already closed");
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        } else if(contact.isPresent()) {
            contact.get().setStatus(AppConstants.CLOSE);
            contactService.save(contact.get());

        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact ID received");
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }
}
