package com.revature.services;

import com.revature.DAOs.TeamDAO;
import com.revature.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    //Have to autowire DAO!

    private TeamDAO teamDAO;

    @Autowired
    public TeamService(TeamDAO teamDAO){
        this.teamDAO = teamDAO;
    }

    public Team insertTeam(Team team){
        //in service layer, check if team values are valid
        if (team.getTeamName() == null || team.getTeamName().isBlank()){
            throw new IllegalArgumentException("Team name cannot be null or blank");
        }

        if (team.getTeamLocation() == null || team.getTeamLocation().isBlank()){
            throw new IllegalArgumentException("Team location cannot be null or blank");
        }

        //if team values are valid, we can send team back to DAO
        return teamDAO.save(team);
    }

    //method for getting all teams from DB
    public List<Team> getAllTeams(){
        return teamDAO.findAll();
    }

    public List<Team> getTeamsByLocation(String location){

        if (location == null || location.isBlank()){
            throw new IllegalArgumentException("Location cannot be null or blank");
        }

        return teamDAO.findByTeamLocation(location);
    }
}
