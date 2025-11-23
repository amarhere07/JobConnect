package com.jobconnect.jobconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jobconnect.jobconnect.dto.AuthResponse;
import com.jobconnect.jobconnect.dto.LoginRequest;
import com.jobconnect.jobconnect.dto.RegisterRequest;
import com.jobconnect.jobconnect.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    	System.out.print(request);
        return ResponseEntity.ok(authService.register(request));
    }
    
    @GetMapping("/home")
    public String getHome(HttpServletRequest request) {
    	return "home";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    	System.out.print(request);
        return ResponseEntity.ok(authService.login(request));
    }
}