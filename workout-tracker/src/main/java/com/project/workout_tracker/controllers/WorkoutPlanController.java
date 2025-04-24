package com.project.workout_tracker.controllers;

import com.project.workout_tracker.dto.WorkoutPlanRequestDTO;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.entities.WorkoutPlan;
import com.project.workout_tracker.services.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutPlanController {

    /*
    GET /workouts – List user’s workouts (JWT)
    POST /workouts – Create a new workout plan
    GET /workouts/:id – Get details of a workout
    PUT /workouts/:id – Update a workout
    DELETE /workouts/:id – Delete a workout
     */

    @Autowired
    private  WorkoutPlanService workoutPlanService;

    @PostMapping()
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutPlanRequestDTO workoutPlan, Authentication authentication){
        try{
            User user= (User) authentication.getPrincipal();
            Long userId= user.getId();
            return new ResponseEntity<>(workoutPlanService.createWorkout(workoutPlan, userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWorkout(@PathVariable("id") Long workoutId, @RequestBody WorkoutPlan updatedWorkoutPlan){
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
    public ResponseEntity<?> getListActivePendingWorkouts(){
        try{
            //if active
            //if pending

            List<WorkoutPlan> workoutPlanList= new ArrayList<>();
            return new ResponseEntity<>(workoutPlanList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





}
