package com.example.playermanagement.service;


import com.example.playermanagement.model.Player;
import com.example.playermanagement.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    IPlayerRepository playerRepository;


    @Override
    public Page<Player> findAll( Pageable pageable, String name) {
        return playerRepository.findAllPlayer(pageable, "%" + name + "%");
    }

    @Override
    public void deletePlayer(int id) {

    }

    @Override
    public void editPLayer(int id, Player player) {

    }

    @Override
    public void save(Player player) {

    }
}
