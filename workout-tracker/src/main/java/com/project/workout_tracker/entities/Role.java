package com.project.workout_tracker.entities;

import com.project.workout_tracker.support.enums.Erole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Erole erole= Erole.ROLE_USER;

    public Role(Erole erole){
        this.erole=erole;
    }
}

