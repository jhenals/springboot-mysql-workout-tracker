package com.project.workout_tracker.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/")
    public ResponseEntity<String> homepage(){
        return new ResponseEntity<>("Workout Trainer Homepage", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(@RequestParam(name = "id") String userId){
        return new ResponseEntity<>("User Dashboard", HttpStatus.OK);
    }
}
