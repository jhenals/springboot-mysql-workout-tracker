package com.project.workout_tracker.support.enums;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Active"), //PENDING
    FINISHED("Finished");

    private final  String status;
    Status(String status){
        this.status= status;
    }
}
