package com.project.workout_tracker.services;

import com.project.workout_tracker.dto.LoginRequest;
import com.project.workout_tracker.dto.SignupRequest;
import com.project.workout_tracker.entities.Role;
import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.repositories.RoleRepository;
import com.project.workout_tracker.repositories.UserRepository;
import com.project.workout_tracker.support.enums.Erole;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AuthenticationService {
    private  final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest loginRequest, HttpServletResponse response){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticationResponse= this.authenticationManager.authenticate(authenticationRequest);

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        jwtService.generateToken(loginRequest.getEmail(), response);
        UserDetails userDetails= (UserDetails) authenticationResponse.getPrincipal();
        return userDetails.getUsername();
    }

    public void registerAccount(SignupRequest signupRequest){
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new EntityExistsException("Email already used.");
        }

        User user = new User(
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Role role= roleRepository.findByErole(Erole.ROLE_USER).orElse(null);
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
    }

    public void logoutUser(HttpServletResponse response){
        jwtService.removeTokenFromCookie(response);
    }
}
