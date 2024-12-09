package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.dto.CustomUserDetails;
import com.tagmaster.codetouch.entity.company.CompanyUser;
import com.tagmaster.codetouch.repository.company.CompanyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CompanyUserRepository companyUserRepository;

    public CustomUserDetailsService(CompanyUserRepository companyUserRepository) {
        this.companyUserRepository = companyUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CompanyUser data = companyUserRepository.findByEmail(email);
        System.out.println(data+"userdetails 값");
        if (data != null) {
            return new CustomUserDetails(data); //userdetails 를 만들어서 userservice 에서 최종적으로 authentication manager에 넘겨줄거다
        }
        return null;
    }
}
