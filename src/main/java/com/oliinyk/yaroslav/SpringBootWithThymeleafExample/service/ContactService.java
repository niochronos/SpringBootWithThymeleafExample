package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ContactDto;
import org.springframework.data.domain.Page;

public interface ContactService {

    boolean saveMessageDetails(ContactDto contactDto);

    Page<ContactDto> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir);

    boolean updateMsgStatus(int contactId);
}
