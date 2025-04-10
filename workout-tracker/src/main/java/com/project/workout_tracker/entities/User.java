package com.project.workout_tracker.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles= new HashSet<>();

    public User(String firstname, String lastname, String username, String email, String encode) {
        this.firstname= firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=encode;
        this.createdAt=LocalDateTime.now();
    }
}
