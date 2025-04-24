package com.project.workout_tracker.services;

import com.project.workout_tracker.dto.WorkoutExerciseDTO;
import com.project.workout_tracker.dto.WorkoutPlanRequestDTO;
import com.project.workout_tracker.dto.WorkoutPlanResponseDTO;
import com.project.workout_tracker.entities.Exercise;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.entities.WorkoutExercise;
import com.project.workout_tracker.entities.WorkoutPlan;
import com.project.workout_tracker.repositories.ExerciseRepository;
import com.project.workout_tracker.repositories.UserRepository;
import com.project.workout_tracker.repositories.WorkoutPlanRepository;
import com.project.workout_tracker.support.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    public WorkoutPlanResponseDTO createWorkout(WorkoutPlanRequestDTO dto, Long userId) {
        return null;
    }

    private WorkoutPlanResponseDTO mapToResponseDTO(WorkoutPlan savedWorkoutPlan) {
        return null;
    }

    public WorkoutPlan scheduleWorkout(Long workoutId, LocalDateTime dateTime) {
        return null;
    }

    public Long deleteWorkout(Long workoutId) {
        return null;
    }

    public WorkoutPlan updateWorkoutPlan(Long workoutId, WorkoutPlan updatedWorkoutPlan) {
        return null;
    }
}
