package com.jobconnect.jobconnect.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.jobconnect.jobconnect.model.Application;
import com.jobconnect.jobconnect.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    public Application applyJob(@PathVariable String jobId, 
    		Authentication authentication) {
    	System.out.print("inside apply job ");
    	String email = authentication.getName();
    	Application application = Application.builder().jobId(jobId).userId(email).status("APPLIED").build();
        return applicationService.applyJob(application);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable String userId) {
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable String jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }
}
