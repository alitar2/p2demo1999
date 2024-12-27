package com.revature.services;

import com.revature.DAOs.TeamDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.Team;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//service layer is where we put our business logic
@Service
public class UserService {

    private final UserDAO userDAO;
    private final TeamDAO teamDAO;

    @Autowired
    public UserService(UserDAO userDAO, TeamDAO teamDAO) {
        this.userDAO = userDAO;
        this.teamDAO = teamDAO;
    }

    @PostMapping
    public User insertUser(IncomingUserDTO user){
        if (user.getUsername() == null || user.getUsername().isBlank() || userDAO.findByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("Username invalid");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()){
            throw new IllegalArgumentException("Password invalid");
        }
        if (user.getRole() == null || user.getRole().isBlank()){
            throw new IllegalArgumentException("Role invalid");
        }
        Optional<Team> currentTeam = teamDAO.findById(user.getTeamID());
        if (currentTeam.isEmpty()){
            throw new IllegalArgumentException("Team invalid");
        }
        return userDAO.save(new User(0,user.getUsername(), user.getPassword(), user.getRole(), currentTeam.get()));
    }

    public User updateUserPassword(int userId, String password){

        //orElseThrow is a cleaner way to handle optionals
        User user = userDAO.findById(userId).orElseThrow(() ->{
            throw new IllegalArgumentException("User not found");
        });

        user.setPassword(password);

        //this won't create a new user, just update it. you can just use save.
        return userDAO.save(user);
    }

    public List<OutgoingUserDTO> getAllUsers(){

        List<OutgoingUserDTO> outgoingUsers = new ArrayList<>();


        //gets all User objects AND sorts them in ascending order by userID.
        List<User> users = userDAO.findAll(Sort.by(Sort.Direction.ASC, "userID"));

        for (User user : users){
            outgoingUsers.add(new OutgoingUserDTO(user.getUserID(), user.getUsername(), user.getRole(), user.getTeam()));
        }

        return outgoingUsers;


    }


}
