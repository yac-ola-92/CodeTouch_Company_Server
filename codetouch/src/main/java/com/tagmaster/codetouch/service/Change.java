package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.entity.company.CompanyUser;
import com.tagmaster.codetouch.repository.company.CompanyUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Change {
    private final CompanyUserRepository companyUserRepository;

    public Change(CompanyUserRepository companyUserRepository) {
        this.companyUserRepository = companyUserRepository;
    }
    public void dateChange(LocalDate birth, String email) {
        CompanyUser companyUser = companyUserRepository.findByEmail(email);
        companyUser.getBirth();

    }
    public void genderChange(LocalDate birth){

        }
    }
}
