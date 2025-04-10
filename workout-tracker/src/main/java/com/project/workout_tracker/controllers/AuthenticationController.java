package com.project.workout_tracker.controllers;

import com.project.workout_tracker.configurations.JwtAuthenticationFilter;
import com.project.workout_tracker.dto.LoginRequest;
import com.project.workout_tracker.dto.SignupRequest;
import com.project.workout_tracker.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService= authenticationService;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        try{
            String email= authenticationService.login(loginRequest, response);
            return new ResponseEntity<>(email+"signed in", HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest, HttpServletRequest request){
        try{
            authenticationService.registerAccount(signupRequest);
            return new ResponseEntity<>("Account registered.", HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("signout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response){
        authenticationService.logoutUser(response);
        return new ResponseEntity<>("You've been signed out!", HttpStatus.OK);
    }

}
