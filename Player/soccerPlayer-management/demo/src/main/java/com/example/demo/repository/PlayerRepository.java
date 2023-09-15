package com.example.demo.repository;

import com.example.demo.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class PlayerRepository implements IPlayerRepository {
    private static final Map<Integer, Player> players;

    static {
        players = new HashMap<>();
        players.put(1, new Player(1, "CT-1", "John", "2000/11/12", "3 năm", "Thủ môn", "Ha Noi"));
        players.put(2, new Player(2, "CT-2", "Bill", "1998/11/06", "3 năm", "Tiền đạo", "Hai Phong"));
        players.put(3, new Player(3, "CT-3", "Alex", "2000/11/02", "3 năm", "Tiền đạo", "Sai Gon"));
        players.put(4, new Player(4, "CT-4", "Adam", "2000/11/04", "3 năm", "Tiền đạo", "Beijing"));
        players.put(5, new Player(5, "CT-5", "Sophia", "2000/11/08", "3 năm", "hậu vệ", "Miami"));
        players.put(6, new Player(6, "CT-6", "Rose", "2000/11/10", "3 năm", "hậu vệ", "NewYork"));
    }

    @Override
    public List<Player> findAll() {
        List<Player> players1 = new ArrayList<>(players.values());
        return players1;
    }

    @Override
    public void save(Player player) {
        players.put(player.getId(),player);
    }

    @Override
    public Player findById(int id) {
        return players.get(id);
    }

    @Override
    public void update(int id, Player player) {
        findAll().set(id,player);
    }

    @Override
    public void remove(int id) {
        players.remove(id);
    }
}
