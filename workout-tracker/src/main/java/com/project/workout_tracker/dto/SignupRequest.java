package com.project.workout_tracker.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
