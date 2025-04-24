package com.project.workout_tracker.controllers;

import com.project.workout_tracker.configurations.JwtAuthenticationFilter;
import com.project.workout_tracker.dto.LoginRequestDTO;
import com.project.workout_tracker.dto.LoginResponseDTO;
import com.project.workout_tracker.dto.SignupRequestDTO;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.services.AuthenticationService;
import com.project.workout_tracker.services.JwtService;
import com.project.workout_tracker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response){
        try{
            User authenticatedUser = authenticationService.login(loginRequest, response);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getJwtExpiresMinutes());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDTO signupRequest, HttpServletRequest request){
        try{
            User user= authenticationService.registerAccount(signupRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logoutUser(HttpServletResponse response){
//        authenticationService.logoutUser(response);
//        return new ResponseEntity<>("You've been signed out!", HttpStatus.OK);
//    }


}
