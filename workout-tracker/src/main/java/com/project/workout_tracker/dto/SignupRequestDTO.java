package com.project.workout_tracker.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
