package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session){

        OutgoingUserDTO user = this.authService.login(loginDTO);



        // if login successful, save user info to session
        // storing this info in session helps us check if user is logged in,
        // validate roles, personalize UI, simplify URLs and reduce path variables
        session.setAttribute("userID", user.getUserID());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        System.out.println(session.getAttribute("username"));
        return ResponseEntity.ok(user);
    }



}

