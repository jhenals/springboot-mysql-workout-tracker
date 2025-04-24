package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}