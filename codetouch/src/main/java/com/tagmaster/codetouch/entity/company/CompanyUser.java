package com.tagmaster.codetouch.entity.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@Table(name = "user")
public class CompanyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //안넣어줬더니 테스트에서 에러남
    private Integer userId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private LocalDate birth;
    private Integer gender;
}
