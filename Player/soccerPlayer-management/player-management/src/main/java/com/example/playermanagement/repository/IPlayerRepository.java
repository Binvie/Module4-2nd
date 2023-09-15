package com.example.playermanagement.repository;

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
    @Query(value = "select * from player where name like :name or position like :name " ,nativeQuery = true)
    Page<Player> findAllPlayer(Pageable pageable, @Param("name")String name);


}
