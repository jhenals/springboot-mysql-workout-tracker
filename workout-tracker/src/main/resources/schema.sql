#########################################################################################
###                                                                                   ###
### Author: Jhenalyn Subol                                                            ###
### License:                                                                          ###
### Date:                                                                             ###
### Version: 1.0                                                                      ###
###                                                                                   ###
#########################################################################################

CREATE SCHEMA IF NOT EXISTS workouttrackerdb;

use workouttrackerd

DROP TABLE IF EXISTS user;
CREATE TABLE workouttrackerdb.user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdAt DATETIME,
    updatedAt DATETIME
)

DROP TABLE IF EXISTS exercises;
CREATE TABLE workouttrackerdb.exercises(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    category VARCHAR(255),
    workout_plan_id BIGINT,
    CONSTRAINT fk_workout_plan
        FOREIGN KEY (workout_plan_id) REFERENCES workout_plans(id)
        ON DELETE CASCADE
)

DROP TABLE IF EXISTS exercise_muscle_groups;
CREATE TABLE workouttrackerdb.exercise_muscle_groups (
    exercise_id BIGINT NOT NULL,
    muscle_group VARCHAR(255) NOT NULL,
    PRIMARY KEY (exercise_id, muscle_group),
    CONSTRAINT fk_exercise
        FOREIGN KEY (exercise_id) REFERENCES exercises(id)
        ON DELETE CASCADE
);




