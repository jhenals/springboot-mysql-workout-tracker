package com.project.workout_tracker.controllers;

import com.project.workout_tracker.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;
    @GetMapping
    public ResponseEntity<?> getAllExercises(){
        try{
            return new ResponseEntity<>(exerciseService.getAllExercises(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
