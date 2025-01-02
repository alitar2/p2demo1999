package com.revature.models.DTOs;

import com.revature.models.Team;

public class OutgoingWithJWT {
    private int userID;
    private String username;
    private String role;
    private Team team;
    private String token;

    public OutgoingWithJWT() {
    }

    public OutgoingWithJWT(int userID, String username, String role, Team team, String token) {
        this.userID = userID;
        this.username = username;
        this.role = role;
        this.team = team;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "OutgoingWithJWT{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                ", token='" + token + '\'' +
                '}';
    }
}
