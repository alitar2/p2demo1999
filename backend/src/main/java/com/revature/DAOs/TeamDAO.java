package com.revature.DAOs;

import com.revature.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDAO extends JpaRepository<Team,Integer> {

    //JPA repository has built in CRUD methods, but we can also make custom queries (property expressions)

    List<Team> findByTeamLocation(String location);


}
