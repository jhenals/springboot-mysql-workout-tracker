package com.project.workout_tracker.support.enums;

import lombok.Getter;

@Getter
public enum Category {
    STRENGTH("Strength"),
    CORE("Core"),
    CARDIO("Cardio"),
    FLEXIBILITY("Flexibility");

    private final  String category;
    Category(String category){
        this.category=category;
    }
}
