package com.example.playermanagement.service;


import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.model.Player;
import com.example.playermanagement.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public Page<Player> findAllPlayer(Pageable pageable, String name) {
        return playerRepository.findAllPlayer(pageable, "%" + name + "%");
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public Optional<Player> findById(int id) {
        return playerRepository.findById(id);
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void remove(int id) {
        playerRepository.deleteById(id);
    }
}
