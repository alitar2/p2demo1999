package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//While you CAN put login and register in the userDAO, it is better practice to separate concerns
public interface AuthDAO extends JpaRepository<User, Integer>{

    User findByUsernameAndPassword(String username, String password);
}
