package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.impl;

import static com.oliinyk.yaroslav.SpringBootWithThymeleafExample.constant.AppConstants.*;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ContactDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.ContactEntity;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.ContactMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.property.AppProperties;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.ContactRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AppProperties appProperties;

    /**
     * Save Contact Details into DB
     * @param contactDto
     * @return boolean
     */
    public boolean saveMessageDetails(ContactDto contactDto) {
        if (contactDto == null) {
            return false;
        }

        contactDto.setStatus(OPEN);
        ContactEntity savedContact = contactRepository.save(
            ContactMapper.toEntity(contactDto)
        );
        return savedContact.getContactId() > 0;
    }

    public Page<ContactDto> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = appProperties.getPageSize();
        if(appProperties.getContact() != null && appProperties.getContact().get("pageSize") != null) {
            pageSize = Integer.parseInt(appProperties.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(
            pageNum - 1,
            pageSize,
            sortDir.equals(ORDER_BY_ASC) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );
        Page<ContactEntity> msgPage = contactRepository.findByStatusWithQuery(OPEN, pageable);
        return msgPage.map(ContactMapper::toDto);
    }

    public boolean updateMsgStatus(int contactId) {
        int rows = contactRepository.updateStatusById(CLOSE, contactId);
//        int rows = contactRepository.updateMsgStatusNative(AppConstants.CLOSE, contactId);
        return rows > 0;
    }
}
