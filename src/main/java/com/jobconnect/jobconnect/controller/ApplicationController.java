package com.jobconnect.jobconnect.controller;

import java.util.List;

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

    @PostMapping("/apply")
    public Application applyJob(@RequestBody Application application) {
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
