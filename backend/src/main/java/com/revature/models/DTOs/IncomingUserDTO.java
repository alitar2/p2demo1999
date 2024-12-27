package com.revature.models.DTOs;

//Data Transfer Object. Used to model incoming JSON data for new user
//When inserting user, we can ignore userID, and use int for team instead of inputting entire team object,
//because ain't nobody got time for that

public class IncomingUserDTO {

    private String username;
    private String password;
    private String role = "Player";
    private int teamID;

    public IncomingUserDTO() {
    }

    public IncomingUserDTO(String username, String password, String role, int teamID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.teamID = teamID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
