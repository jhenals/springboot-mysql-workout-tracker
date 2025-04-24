package com.project.workout_tracker.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutPlanRequestDTO {
    private String name;
    private LocalDateTime scheduledAt;
    private String comments;
    private List<WorkoutExerciseDTO> exercises;

}
