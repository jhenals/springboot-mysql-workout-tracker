package com.project.workout_tracker.controllers;

import com.project.workout_tracker.dto.WorkoutPlanRequestDTO;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.entities.WorkoutPlan;
import com.project.workout_tracker.services.WorkoutPlanService;
import com.project.workout_tracker.support.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutPlanController {

    /*
    Create Workout: Allow users to create workouts composed of multiple exercises.
    Update Workout: Allow users to update workouts and add comments.
    Delete Workout: Allow users to delete workouts.
    Schedule Workouts: Allow users to schedule workouts for specific dates and times.
    List Workouts: List active or pending workouts sorted by date and time.
     */

    @Autowired
    private  WorkoutPlanService workoutPlanService;


    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutPlanRequestDTO workoutPlan, @AuthenticationPrincipal User user){
        try{
            Long userId= user.getId();
            return new ResponseEntity<>(workoutPlanService.createWorkout(workoutPlan, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWorkout(@PathVariable("id") Long workoutId,  @RequestBody WorkoutPlanRequestDTO updatedWorkoutPlan){
        try{
            return new ResponseEntity<>(workoutPlanService.updateWorkoutPlan(workoutId, updatedWorkoutPlan), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteWorkout(@RequestParam(name = "id") Long workoutId){
        try{
            return  new ResponseEntity<>(workoutPlanService.deleteWorkout(workoutId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}/schedule", method = RequestMethod.PUT)
    public ResponseEntity<?> scheduleWorkout(@PathVariable("id") Long workoutId, LocalDateTime dateTime){
        try{
            return new ResponseEntity<>(workoutPlanService.scheduleWorkout(workoutId, dateTime), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllWorkouts(){
        return new ResponseEntity<>(workoutPlanService.getAllWorkouts(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getWorkoutPlansOfUser(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(workoutPlanService.getWorkoutPlansOfUser(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getListWorkouts(@PathVariable("status") Status status, @AuthenticationPrincipal User user){
        List<WorkoutPlan> workoutPlanList;
        try{
            switch (status){
                case ACTIVE -> workoutPlanList= workoutPlanService.getListWorkouts(Status.ACTIVE, user.getId());
                case FINISHED -> workoutPlanList= workoutPlanService.getListWorkouts(Status.FINISHED, user.getId());
                default -> workoutPlanList=null;
            }
            return new ResponseEntity<>(workoutPlanList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





}
