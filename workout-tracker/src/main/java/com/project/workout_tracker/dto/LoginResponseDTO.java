package com.project.workout_tracker.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Long expiresIn;
}
