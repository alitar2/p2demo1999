package com.revature.controllers;

import com.revature.models.Team;
import com.revature.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Combines @Controller and @ResponseBody
@RequestMapping("/teams") //Root URL + /teams
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class TeamController {

    private final TeamService teamService;

    //Constructor Injection is ALWAYS better than field injection!
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> insertTeam(@RequestBody Team team){

        Team newTeam = this.teamService.insertTeam(team);

        return ResponseEntity.ok(newTeam);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> teams = this.teamService.getAllTeams();

        return ResponseEntity.ok(teams);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<?> getTeamsByLocation(@PathVariable String location){
        List<Team> teams = this.teamService.getTeamsByLocation(location);

        //If list is empty, send 404 Not Found with message
        if (teams.isEmpty()){
            return ResponseEntity.status(404).body("No teams found in " + location);
        }

        return ResponseEntity.ok(teams);
    }

}
