package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    List<ContactEntity> findByStatus(String status);

    @Query("SELECT c FROM ContactEntity c WHERE c.status = :status")
        //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    Page<ContactEntity> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE ContactEntity c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

//    Page<ContactEntity> findOpenMsgs(@Param("status") String status, Pageable pageable);

//    @Transactional
//    @Modifying
//    int updateMsgStatus(String status, int id);

//    @Query(nativeQuery = true)
//    Page<ContactEntity> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true)
//    int updateMsgStatusNative(String status, int id);
}
