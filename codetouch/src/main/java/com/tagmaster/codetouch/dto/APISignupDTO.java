package com.tagmaster.codetouch.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class APISignupDTO {
    private String name;
    private String phone;
    private LocalDate birth;
    private Integer gender;
}
