package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity getByRoleName(String roleName);
}
