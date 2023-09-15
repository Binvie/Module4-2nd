package com.example.demo.repository;

import com.example.demo.model.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> findAll();

    void save(Player player);

    Player findById(int id);

    void update(int id, Player player);

    void remove(int id);

}
