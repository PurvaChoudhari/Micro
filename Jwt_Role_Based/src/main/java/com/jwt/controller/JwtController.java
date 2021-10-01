package com.jwt.controller;

import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.service.JwtService;
//import com.jwt.service.JwtService;
import com.jwt.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin

public class JwtController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    
   
    
}
