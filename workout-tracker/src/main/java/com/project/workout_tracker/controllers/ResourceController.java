package com.project.workout_tracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/")
    public ResponseEntity<String> homepage(){
        return new ResponseEntity<>("Workout Trainer Homepage", HttpStatus.OK);
    }
}
