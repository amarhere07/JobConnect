package com.jobconnect.jobconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "users")
public class User {

	@Id
	private String id;
	
	@NotBlank(message = "Username is mandatory")
	private String userName;
	
	@Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	private Role role;
}
