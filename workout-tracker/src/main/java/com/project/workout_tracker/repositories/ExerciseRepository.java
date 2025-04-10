package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.Exercise;
import com.project.workout_tracker.support.enums.Category;
import com.project.workout_tracker.support.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e WHERE :mg MEMBER OF e.muscleGroups")
    List<Exercise> findExercisesByMuscleGroup(@Param("mg") MuscleGroup mg);

    @Query("SELECT e FROM Exercise e WHERE e.category= :cat")
    List<Exercise> findExercisesByCategory(@Param("cat") Category cat);
}