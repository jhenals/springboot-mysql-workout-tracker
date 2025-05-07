package com.project.workout_tracker.dto;

import lombok.Data;

@Data
public class WorkoutExerciseRequestDTO {
    private long exerciseId;
    private int sets;
    private int repetitions;
    private float weight;
}
