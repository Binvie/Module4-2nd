package com.example.playermanagement.service;

import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlayerService extends IGenerate<Player> {

    Page<Player> findAllPlayer(Pageable pageable, String name);
}
