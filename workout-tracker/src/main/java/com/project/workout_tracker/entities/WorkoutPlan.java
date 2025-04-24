package com.project.workout_tracker.entities;

import com.project.workout_tracker.support.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    private List<WorkoutExercise> exerciseList= new ArrayList<>();

    private LocalDateTime scheduledAt;

    @Column(name = "status")
    private Status status;

    @Column(name = "comment")
    private String comment;


}
