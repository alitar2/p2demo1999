package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.controllers.AuthController;
import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    //login and register methods go here

    private final AuthDAO authDAO;

    @Autowired
    public AuthService(AuthDAO authDAO){
        this.authDAO = authDAO;
    }

    public OutgoingUserDTO login(LoginDTO loginDTO){
        User u = authDAO.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (u == null){
            throw new IllegalArgumentException("Invalid credentials");
        }


        System.out.println("User "+u.getUsername()+" logged in");

        return new OutgoingUserDTO(u.getUserID(), u.getUsername(), u.getRole(), u.getTeam());
    }

}
