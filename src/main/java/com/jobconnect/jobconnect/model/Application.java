package com.jobconnect.jobconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "applications")
public class Application {

    @Id
    private String id;

    private String jobId;
    private String userId;
    private String resumeLink;
    private String status; // Applied, Reviewed, Shortlisted
}
