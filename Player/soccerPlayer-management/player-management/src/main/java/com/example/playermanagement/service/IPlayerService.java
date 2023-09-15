package com.example.playermanagement.service;

import com.example.playermanagement.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlayerService {
    Page<Player> findAll(Pageable pageable, String name);

    void deletePlayer(int id);

    void editPLayer(int id, Player player);

    void save(Player player);
}
