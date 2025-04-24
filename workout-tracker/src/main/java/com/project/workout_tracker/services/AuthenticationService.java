package com.project.workout_tracker.services;

import com.project.workout_tracker.dto.LoginRequestDTO;
import com.project.workout_tracker.dto.SignupRequestDTO;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private  final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
            ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerAccount(SignupRequestDTO signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new EntityExistsException("Email already used.");
        }

        User user = new User(
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }

    public User login(LoginRequestDTO loginRequest, HttpServletResponse response){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
    }


//    public void logoutUser(HttpServletResponse response){
//        jwtService.removeTokenFromCookie(response);
//    }
}
