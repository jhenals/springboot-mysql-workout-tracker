package com.project.workout_tracker.support.mappers;

import com.project.workout_tracker.dto.WorkoutPlanResponseDTO;
import com.project.workout_tracker.entities.WorkoutExercise;
import com.project.workout_tracker.entities.WorkoutPlan;

import java.util.ArrayList;
import java.util.List;


public class WorkoutPlanMappers {

    public static WorkoutPlanResponseDTO mapWorkoutPlanToDto (WorkoutPlan workoutPlan){
        WorkoutPlanResponseDTO workoutPlanResponseDTO= new WorkoutPlanResponseDTO();
        workoutPlanResponseDTO.setId(workoutPlan.getId());
        workoutPlanResponseDTO.setName(workoutPlan.getName());
        workoutPlanResponseDTO.setStatus(workoutPlan.getStatus());
        workoutPlanResponseDTO.setScheduledAt(workoutPlan.getScheduledAt());
        workoutPlanResponseDTO.setCreatedAt(workoutPlan.getCreatedAt());
        workoutPlanResponseDTO.setExercises(convertListFromEntityToId(workoutPlan));
        return workoutPlanResponseDTO;

    }

    private static List<Long> convertListFromEntityToId(WorkoutPlan workoutPlan) {
        List<Long> listId= new ArrayList<>();
        for(WorkoutExercise workoutExercise:workoutPlan.getExerciseList()){
            listId.add(workoutExercise.getId());
        }
        return listId;
    }
}
