package com.project.workout_tracker.services;

import com.project.workout_tracker.dto.WorkoutExerciseRequestDTO;
import com.project.workout_tracker.dto.WorkoutPlanRequestDTO;
import com.project.workout_tracker.dto.WorkoutPlanResponseDTO;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.entities.WorkoutExercise;
import com.project.workout_tracker.entities.WorkoutPlan;
import com.project.workout_tracker.repositories.UserRepository;
import com.project.workout_tracker.repositories.WorkoutExerciseRepository;
import com.project.workout_tracker.repositories.WorkoutPlanRepository;
import com.project.workout_tracker.support.enums.Status;
import com.project.workout_tracker.support.exceptions.CustomException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.workout_tracker.support.mappers.WorkoutPlanMappers.mapWorkoutPlanToDto;


@Service
public class WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;


    @Autowired
    private UserRepository userRepository;

    public WorkoutPlanResponseDTO createWorkout(WorkoutPlanRequestDTO dto,  Long userId) {
        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow( ()-> new UsernameNotFoundException("User not found.") );

        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(dto.getName());
        workoutPlan.setComments(dto.getComments());
        workoutPlan.setScheduledAt(dto.getScheduledAt());
        workoutPlan.setCreatedAt(LocalDateTime.now());
        workoutPlan.setStatus(Status.ACTIVE);
        workoutPlan.setUser(user);


        List<WorkoutExercise> workoutExerciseList = convertListFromIdToEntity(workoutPlan,dto.getWorkoutExerciseDTOList());
        workoutPlan.setExerciseList(workoutExerciseList);
        workoutPlanRepository.save(workoutPlan);

        return mapWorkoutPlanToDto(workoutPlan);

    }

    private List<WorkoutExercise> convertListFromIdToEntity(WorkoutPlan workoutPlan, List<WorkoutExerciseRequestDTO> dtoList) {
        List<WorkoutExercise> workoutExerciseList= new ArrayList<>();
        for(WorkoutExerciseRequestDTO workoutExerciseDTO : dtoList){
            WorkoutExercise workoutExercise= new WorkoutExercise();
            workoutExercise.setWorkoutPlan(workoutPlan);
            workoutExercise.setSets(workoutExerciseDTO.getSets());
            workoutExercise.setRepetitions(workoutExerciseDTO.getRepetitions());
            workoutExercise.setWeight(workoutExerciseDTO.getWeight());
            workoutExerciseRepository.save(workoutExercise);
            workoutExerciseList.add(workoutExercise);
        }
        return workoutExerciseList;
    }


    public WorkoutPlanResponseDTO scheduleWorkout(Long workoutId, LocalDateTime dateTime) {
        WorkoutPlan workoutPlan= workoutPlanRepository.findById(workoutId)
                .orElseThrow( ()-> new CustomException("Workout Plan doesn't exist."));

        workoutPlan.setScheduledAt(dateTime);
        workoutPlanRepository.save(workoutPlan);
        return mapWorkoutPlanToDto(workoutPlan);
    }

    public Long deleteWorkout(Long workoutPlanId) {
        Optional<WorkoutPlan> workoutPlanOptional= workoutPlanRepository.findById(workoutPlanId);
        if(workoutPlanOptional.isPresent()){
            workoutPlanRepository.deleteById(workoutPlanId);
        }else{
            throw  new EntityExistsException("Workout plan doesn't exist");
        }

        return workoutPlanId;
    }

    public WorkoutPlanResponseDTO updateWorkoutPlan(Long workoutPlanId, WorkoutPlanRequestDTO updatedWorkoutPlan) {
        WorkoutPlan workoutPlan= workoutPlanRepository.findById(workoutPlanId).
                orElseThrow( ()-> new CustomException("Workout plan doesn't exist"));

        workoutPlan.setName(updatedWorkoutPlan.getName());
        workoutPlan.setComments(updatedWorkoutPlan.getComments());
        workoutPlan.setStatus(updatedWorkoutPlan.getStatus());

        List<WorkoutExercise> workoutExerciseList = convertListFromIdToEntity(workoutPlan, updatedWorkoutPlan.getWorkoutExerciseDTOList());
        workoutPlan.setExerciseList(workoutExerciseList);

        workoutPlanRepository.save(workoutPlan);

        return mapWorkoutPlanToDto(workoutPlan);

    }

    public List<WorkoutPlan> getListWorkouts(Status status, Long userId) {
        return new ArrayList<>(workoutPlanRepository.findByStatusAndUser(status, userId));
    }

    public List<WorkoutPlan> getAllWorkouts() {
        return new ArrayList<>(workoutPlanRepository.findAll());
    }

    public List<WorkoutPlan> getWorkoutPlansOfUser(Long userId){
        return new ArrayList<>(workoutPlanRepository.findByUserId(userId));

    }
}
