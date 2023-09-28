package com.example.playermanagement.repository;

import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface IPlayerRepository  extends JpaRepository<Player,Integer> {
    @Query(value = "select * " +
            "from player where name like :name " ,nativeQuery = true)
    Page<Player> findAllPlayer(Pageable pageable, @Param("name")String name);

    @Query(value = "select count(t.name) as tong \n" +
            "from player\n" +
            "join team as t on t.id = player.team_id\n" +
            "where t.name = :team",nativeQuery = true)
    int numberPlayer(@Param("team") String name);

    @Transactional
    @Modifying
    @Query(value = "update player set status = 0 where id = :id" ,nativeQuery = true)
    void activePlayer(@Param("id") int id);
}
