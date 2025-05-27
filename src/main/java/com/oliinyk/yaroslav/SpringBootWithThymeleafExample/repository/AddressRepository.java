package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
