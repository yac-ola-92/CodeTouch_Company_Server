package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.dto.APISignupDTO;
import com.tagmaster.codetouch.service.identity.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/pass")
public class PASSCtrl {

   private final AuthService authService;

   @Autowired
   public PASSCtrl(AuthService authService) {
       this.authService = authService;
   }
   @GetMapping("/certifications/{imp_uid}")
   public ResponseEntity<Map<String,Object>> getCertifications(@PathVariable String imp_uid) throws Exception {
       Map<String,Object> response = new HashMap<>();
       String accessToken = authService.TokenService();
       APISignupDTO dto = authService.AuthReqService(imp_uid, accessToken);
       response.put("message", "api로 개인정보 받기 성공");
       response.put("data", dto);
       return ResponseEntity.ok(response);
   }
}

