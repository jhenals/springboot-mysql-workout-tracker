package com.project.workout_tracker.seeder;

import com.project.workout_tracker.entities.Exercise;
import com.project.workout_tracker.repositories.ExerciseRepository;
import com.project.workout_tracker.support.enums.Category;
import com.project.workout_tracker.support.enums.MuscleGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ExerciseSeeder implements ApplicationRunner {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public void run (ApplicationArguments applicationArguments) throws  Exception{
        seedExercise();
    }

    private void seedExercise(){
        List<Exercise> exerciseList = new ArrayList<>();

        Exercise squat = new Exercise();
        squat.setName("Squat");
        squat.setDescription("Strengthens your legs and glutes.");
        squat.setCategory(Category.STRENGTH);
        squat.setMuscleGroups(Set.of(MuscleGroup.LEGS, MuscleGroup.GLUTES));

        Exercise pushUp = new Exercise();
        pushUp.setName("Push-Up");
        pushUp.setDescription("Strengthens chest, shoulders, and triceps.");
        pushUp.setCategory(Category.STRENGTH);
        pushUp.setMuscleGroups(Set.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS));

        Exercise running= new Exercise();
        running.setName("Running");
        running.setDescription("Improves cardio endurance");
        running.setCategory(Category.CARDIO);
        running.setMuscleGroups(Set.of(MuscleGroup.LEGS, MuscleGroup.CORE));

        List<Exercise> newExercises= List.of(squat, pushUp, running);
        for( Exercise ex: newExercises){
            if(!exerciseRepository.existsByName(ex.getName())){
                exerciseList.add(ex);
            }
        }
        if(!exerciseList.isEmpty()){
            exerciseRepository.saveAll(List.of(squat, pushUp, running));
            log.info("Seeded {} exercises into the database", 3);
        }else{
            log.info("All  exercisesalready exist. No seeding needed.");
        }
    }

}
