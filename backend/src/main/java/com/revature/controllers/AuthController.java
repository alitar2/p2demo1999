package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.DTOs.OutgoingWithJWT;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    // Autowire AuthenicationManager and JwtTokenUtil
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            // if auth successful, build user based on logged in data
            User user = (User) auth.getPrincipal();

            // generate JWT
            String token = jwtTokenUtil.generateAccessToken(user);

            // in real app, returning JWT would be sufficient as it has all info
            // but for this project, we will return outgoing userDTO
            return ResponseEntity.ok(new OutgoingWithJWT(user.getUserID(), user.getUsername(), user.getRole(),user.getTeam(),token));
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }



}

