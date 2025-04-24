package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
}