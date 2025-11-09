package com.jobconnect.jobconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobconnect.jobconnect.model.Job;
import com.jobconnect.jobconnect.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(String id) {
        return jobRepository.findById(id);
    }

    public List<Job> getJobsByEmployerEmail(String email) {
        return jobRepository.findByPostedByEmail(email);
    }

    public List<Job> searchByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> searchByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(id);
    }

    public Job updateJob(String id, Job updated) {
        updated.setId(id);
        return jobRepository.save(updated);
    }
}
