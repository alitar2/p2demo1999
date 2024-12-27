package com.revature.models.DTOs;


import com.revature.models.Team;

// common DTO: so we can send user info without password
public class OutgoingUserDTO {

    private int userID;
    private String username;
    private String role;
    private Team team;

    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(int userID, String username, String role, Team team) {
        this.userID = userID;
        this.username = username;
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

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                '}';
    }
}
