package com.jobconnect.jobconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "jobs")
public class Job {

    @Id
    private String id;

    private String title;
    private String company;
    private String location;
    private String type; // e.g. "Full-Time", "Part-Time", "Remote"
    private String salary;
    
    // short description / summary
    private String summary;

    // full description (long text)
    private String description;

    // who posted the job — we store poster email (safer until you add userId in JWT)
    private String postedByEmail;

    // optionally postedById if you can extract user id
    private String postedById;
}
