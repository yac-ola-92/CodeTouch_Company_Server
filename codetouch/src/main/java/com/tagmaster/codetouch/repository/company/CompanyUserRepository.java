package com.tagmaster.codetouch.repository.company;


import com.tagmaster.codetouch.entity.company.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Integer> {
    Boolean existsByEmail(String email);
    CompanyUser findByEmail(String email);
}
