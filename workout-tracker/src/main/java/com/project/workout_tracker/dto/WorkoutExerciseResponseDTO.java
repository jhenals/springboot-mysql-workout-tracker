package com.project.workout_tracker.dto;

import lombok.Data;

@Data
public class WorkoutExerciseResponseDTO {
    private Long id;
    private Long exerciseId;
    private Long workoutPlanId;
    private int sets;
    private int repetitions;
    private float weight;
}
