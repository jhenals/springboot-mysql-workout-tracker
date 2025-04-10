package com.project.workout_tracker.configurations;

import com.project.workout_tracker.entities.User;
import com.project.workout_tracker.services.CustomUserDetailService;
import com.project.workout_tracker.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private  final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailService customUserDetailService){
        this.jwtService=jwtService;
        this.customUserDetailService= customUserDetailService;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = jwtService.getJwtFromCookie(request);
            jwtService.validateToken(jwt);
            String userEmail = jwtService.extractEmail();

            UserDetails userDetails= customUserDetailService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext context= SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }catch (Exception e){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        filterChain.doFilter(request,response);
    }


}
