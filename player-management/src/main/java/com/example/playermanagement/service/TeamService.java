package com.example.playermanagement.service;

import com.example.playermanagement.model.Team;
import com.example.playermanagement.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeamService implements ITeamService{
    @Autowired
    private ITeamRepository teamRepository;
    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Team team) {

    }

    @Override
    public void remove(int id) {

    }
}
