package com.example.demo.service;

import com.example.demo.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    void save(Player player);

    Player findById(int id);

    void update(int id, Player player);

    void remove(int id);
}
