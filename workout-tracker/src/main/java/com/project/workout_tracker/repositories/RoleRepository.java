package com.project.workout_tracker.repositories;

import com.project.workout_tracker.entities.Role;
import com.project.workout_tracker.support.enums.Erole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByErole(Erole erole);
}