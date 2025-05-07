package com.project.workout_tracker.dto;

import com.project.workout_tracker.entities.WorkoutExercise;
import com.project.workout_tracker.support.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkoutPlanRequestDTO {
    private String name;
    private LocalDateTime scheduledAt;
    private String comments;
    private Status status;
    private List<WorkoutExerciseRequestDTO> workoutExerciseDTOList;
}
