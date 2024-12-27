package com.revature.controllers;


import com.revature.aspects.AdminOnly;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller layer is where we put our endpoints
@RestController //combines @Controller and @ResponseBody
@RequestMapping("/users")
// need to add @CrossOrigin to avoid CORS issues and allow the front end to communicate with the back end
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @AdminOnly //only admins can access this method
    @GetMapping
    public ResponseEntity<List<OutgoingUserDTO>> getAllUsers(){
        List<OutgoingUserDTO> users = this.userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody IncomingUserDTO userDTO){
        User newUser = this.userService.insertUser(userDTO);
        return ResponseEntity.status(201).body(newUser);
    }

    @PatchMapping("/password/{userId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable int userId, @RequestBody String password){

        //202 accepted
        return ResponseEntity.accepted().body(this.userService.updateUserPassword(userId, password));
    }


}
