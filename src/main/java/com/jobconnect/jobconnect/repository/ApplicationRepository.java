package com.jobconnect.jobconnect.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.jobconnect.jobconnect.model.Application;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    List<Application> findByJobId(String jobId);
    List<Application> findByUserId(String userId);
}
