package com.project.workout_tracker.services;

import com.project.workout_tracker.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

import com.project.workout_tracker.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
       return new ArrayList<>(exerciseRepository.findAll());
    }
}
