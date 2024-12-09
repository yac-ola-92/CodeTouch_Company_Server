package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.dto.SignupDTO;
import com.tagmaster.codetouch.entity.company.CompanyUser;
import com.tagmaster.codetouch.repository.company.CompanyUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SignupService {
    private final CompanyUserRepository companyUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignupService(CompanyUserRepository companyUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.companyUserRepository = companyUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }
    public void SignupProcess(SignupDTO signupDTO) {
        System.out.println(signupDTO);
        String email = signupDTO.getEmail();
        String password = signupDTO.getPassword();
        String name = signupDTO.getName();
        String nickname = signupDTO.getNickname();
        Integer gender = signupDTO.getGender();
        LocalDate birth = signupDTO.getBirth();

        Boolean isExist = companyUserRepository.existsByEmail(email);

        if (isExist) {

            return;
        }

        CompanyUser data = new CompanyUser();

        data.setNickname(nickname);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setName(name);
        data.setEmail(email);
        data.setGender(gender);
        data.setBirth(birth);

        companyUserRepository.save(data);
    }

}
