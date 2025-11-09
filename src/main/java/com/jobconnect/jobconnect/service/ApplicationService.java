package com.jobconnect.jobconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobconnect.jobconnect.model.Application;
import com.jobconnect.jobconnect.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application applyJob(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(String userId) {
        return applicationRepository.findByUserId(userId);
    }

    public List<Application> getApplicationsByJob(String jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
