package com.revature.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component //stereotype annotation
@Entity // makes db table based on model class
@Table(name="users") //specifies properties, in this case table name
public class User implements UserDetails {

    @Id //Primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY) //makes PK auto increment integers
    private int userID;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "player"; //player has been set as default role!

    // FK to team
    // Obvious question: why not just store teamID instead of a whole ass team object in this model?
    // This object storing enables us to leverage DTOs, which gives us more control over flow of data from client to server,
    // and removes clutter from other layers.
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="teamID")
    private Team team;


    //add password field


    //boilerplate code --- no args, all args, getter/setter, toString


    public User() {
    }

    public User(int userID, String username, String password, String role, Team team) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.team = team;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    //do note, toString is useful for debugging!

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                '}';
    }
}
