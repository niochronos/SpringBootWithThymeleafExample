package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ContactDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    boolean saveMessageDetails(ContactDto contactDto);

    Page<ContactDto> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir);

    boolean updateMsgStatus(int contactId);

    List<ContactDto> findByStatus(String status);

    ContactDto save(ContactDto contactDto);

    void deleteById(int id);

    Optional<ContactDto> findById(int id);
}
