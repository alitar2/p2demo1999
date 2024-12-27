package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;


//models are used to represent data in the database
@Component
@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamID;

    @Column(nullable = false)
    private String teamName;

    private String teamLocation;

    // FK to user!!!
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore //ignores this field when converting to JSON, to avoid infinite recursion
    private List<User> users;


    //boilerplate code


    public Team() {
    }

    public Team(int teamID, String teamName, String teamLocation) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamLocation = teamLocation;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //do note, toString is useful for debugging!
    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + teamID +
                ", teamName=" + teamName +
                ", teamLocation='" + teamLocation + '\'' +
                '}';
    }
}
