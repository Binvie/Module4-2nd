package com.example.playermanagement.repository;

import com.example.playermanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team,Integer> {

}
