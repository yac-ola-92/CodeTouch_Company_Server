package com.tagmaster.codetouch.entity.customer;

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
@NoArgsConstructor
@Table(name = "user")
public class CustomerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private int siteId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private LocalDate birth;
    private int gender;
    @Column(columnDefinition = "json")
    private String address;
    private Integer role;
    private int mileage;
    private int businessNum;
    private int reportNum;
}
