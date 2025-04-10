package com.project.workout_tracker.services;

import com.project.workout_tracker.entities.Exercise;
import com.project.workout_tracker.repositories.ExerciseRepository;
import com.project.workout_tracker.support.enums.Category;
import com.project.workout_tracker.support.enums.MuscleGroup;
import com.project.workout_tracker.support.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise addNewExercise(Exercise exercise){
        return exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(long id){
        Optional<Exercise> exerciseOptional= exerciseRepository.findById(id);
        if( exerciseOptional.isPresent()) {
            return exerciseOptional.get();
        }else{
            throw new CustomException("Exercise not found");
        }
    }

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    public List<Exercise> getAllExcerciseByMuscleGroup(MuscleGroup mg){
        return exerciseRepository.findExercisesByMuscleGroup(mg);
    }

    public List<Exercise> getAllExerciseByCategory(Category cat){
        return exerciseRepository.findExercisesByCategory(cat);
    }

    public Exercise updateExercise(long id, Exercise e){
        Optional<Exercise> exerciseOptional= exerciseRepository.findById(id);
        if(exerciseOptional.isPresent()){
            Exercise existingExercise= exerciseOptional.get();
            exerciseRepository.delete(existingExercise);
            exerciseRepository.save(e);
        }
        return e;
    }

    public Exercise deleteExercise(long id){
        Optional<Exercise> exerciseOptional= exerciseRepository.findById(id);
        Exercise e=null;
        if(exerciseOptional.isPresent()){
            e=exerciseOptional.get();
            exerciseRepository.deleteById(id);
        }
        return e;
    }

}
