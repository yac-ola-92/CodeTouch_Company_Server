package com.tagmaster.codetouch.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SignupDTO {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String phone;
    private LocalDate birth;
    private Integer gender;
}
