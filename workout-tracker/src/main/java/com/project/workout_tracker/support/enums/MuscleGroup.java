package com.project.workout_tracker.support.enums;

import lombok.Getter;

@Getter
public enum MuscleGroup {
    CHEST("chest"),
    BACK("back"),
    LEGS("leg"),
    ARMS("arms"),
    CORE("core"),
    SHOULDERS("shoulders"),
    FULL_BODY("full_body"),
    GLUTES("glutes");

    private final String musclegroup;

    MuscleGroup(String muscleGroup){
        this.musclegroup=muscleGroup;
    }

}
