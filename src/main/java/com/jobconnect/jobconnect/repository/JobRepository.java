package com.jobconnect.jobconnect.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.jobconnect.jobconnect.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByPostedByEmail(String email);
    List<Job> findByLocationContainingIgnoreCase(String location);
    List<Job> findByTitleContainingIgnoreCase(String title);
}
