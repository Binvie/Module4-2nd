package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    IPlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Player findById(int id) {
        return playerRepository.findById(id);
    }

    @Override
    public void update(int id, Player player) {
        playerRepository.update(id,player);
    }

    @Override
    public void remove(int id) {
        playerRepository.remove(id);
    }
}
