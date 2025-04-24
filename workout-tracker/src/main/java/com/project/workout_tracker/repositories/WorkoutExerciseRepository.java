package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
}