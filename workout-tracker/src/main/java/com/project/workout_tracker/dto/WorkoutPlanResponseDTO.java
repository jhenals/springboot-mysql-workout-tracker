package com.project.workout_tracker.dto;


import com.project.workout_tracker.support.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutPlanResponseDTO {
    private Long id;
    private Long userId;
    private String name;
    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;
    private String comments;
    private Status status;
    private List<Long> exercises;

}
