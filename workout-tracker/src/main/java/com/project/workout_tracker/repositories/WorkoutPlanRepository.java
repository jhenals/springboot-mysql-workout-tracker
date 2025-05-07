package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.WorkoutPlan;
import com.project.workout_tracker.support.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findByStatus(Status status);

    @Query("SELECT w FROM WorkoutPlan w where w.user.id = :userId and w.status= :status")
    List<WorkoutPlan> findByStatusAndUser(@Param("status")Status status, @Param("userId")Long userId);

    List<WorkoutPlan> findByUserId(Long userId);

    /*
    List<WorkoutPlan> findByStatusAndUserId(Status status, Long userId);
     */
}