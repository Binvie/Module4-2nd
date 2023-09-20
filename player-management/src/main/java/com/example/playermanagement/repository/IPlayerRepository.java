package com.example.playermanagement.repository;

import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPlayerRepository  extends JpaRepository<Player,Integer> {
    @Query(value = "select pl.id, pl.code, pl.name, pl.date_of_birth as dateOfBirth, pl.experience, pl.avatar, p.position_name as positionName " +
            "from player as pl " +
            "join position as p on p.id = pl.position_id " +
            "where pl.name like :name " ,nativeQuery = true)
    Page<IPlayerDto> findAllPlayer(Pageable pageable, @Param("name")String name);
}
