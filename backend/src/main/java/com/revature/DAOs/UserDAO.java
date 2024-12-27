package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//DAOs are used to interact with the database
public interface UserDAO extends JpaRepository<User,Integer> {

    public List<User> findByTeamTeamID(int teamID);

    public User findByUsername(String username);
}
