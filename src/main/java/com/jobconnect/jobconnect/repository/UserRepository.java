package com.jobconnect.jobconnect.repository;

import com.jobconnect.jobconnect.model.User; // ✅ Correct import
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName); // note: userName matches your model
    Optional<User> findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}