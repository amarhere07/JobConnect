package com.jobconnect.jobconnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jobconnect.jobconnect.dto.JobDto;
import com.jobconnect.jobconnect.model.Job;
import com.jobconnect.jobconnect.service.JobService;
import com.jobconnect.jobconnect.repository.UserRepository;
import com.jobconnect.jobconnect.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class JobController {

    private final JobService jobService;
    private final UserRepository userRepository; // assumed to exist

    // Anyone (authenticated or not) can view all jobs — change to authenticated only if needed
    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // get single job
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable String id) {
        Optional<Job> job = jobService.getJobById(id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // search by title / location
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location) {

        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(jobService.searchByTitle(title));
        }
        if (location != null && !location.isBlank()) {
            return ResponseEntity.ok(jobService.searchByLocation(location));
        }
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    /**
     * Create job — only EMPLOYER role allowed.
     * We set postedByEmail from authenticated principal (authentication.getName()).
     */
    @PostMapping("/create-job")
    @PreAuthorize("hasAuthority('EMPLOYER')") // requires method security enabled (you already have @EnableMethodSecurity)
    public ResponseEntity<Job> createJob(@RequestBody JobDto dto, Authentication authentication) {
        String email = authentication.getName();
        Job job = Job.builder()
                .title(dto.getTitle())
                .company(dto.getCompany())
                .location(dto.getLocation())
                .type(dto.getType())
                .salary(dto.getSalary())
                .summary(dto.getSummary())
                .description(dto.getDescription())
                .postedByEmail(email)
                .build();

        // If you want to also store user id:
        userRepository.findByEmail(email).ifPresent(u -> job.setPostedById(u.getId()));

        Job saved = jobService.createJob(job);
        return ResponseEntity.ok(saved);
    }

    // Employer can update their own job (or admins)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public ResponseEntity<?> updateJob(@PathVariable String id, @RequestBody JobDto dto, Authentication authentication) {
        String email = authentication.getName();
        Optional<Job> existing = jobService.getJobById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Job job = existing.get();
        // authorize: only poster can update
        if (!email.equals(job.getPostedByEmail())) {
            return ResponseEntity.status(403).body("You are not allowed to update this job");
        }

        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setType(dto.getType());
        job.setSalary(dto.getSalary());
        job.setSummary(dto.getSummary());
        job.setDescription(dto.getDescription());

        Job updated = jobService.updateJob(id, job);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public ResponseEntity<?> deleteJob(@PathVariable String id, Authentication authentication) {
        String email = authentication.getName();
        Optional<Job> existing = jobService.getJobById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Job job = existing.get();
        if (!email.equals(job.getPostedByEmail())) {
            return ResponseEntity.status(403).body("You are not allowed to delete this job");
        }
        jobService.deleteJob(id);
        return ResponseEntity.ok("Deleted");
    }

    // get jobs posted by authenticated employer
    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Job>> getMyJobs(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(jobService.getJobsByEmployerEmail(email));
    }
}
