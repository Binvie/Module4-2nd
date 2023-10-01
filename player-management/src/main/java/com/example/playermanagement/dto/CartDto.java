package com.example.playermanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private List<PlayerDto> playerDtoList = new ArrayList<>();

    public CartDto() {
    }
    public void addPlayer(PlayerDto playerDto){
        if (!playerDtoList.contains(playerDto)){
            playerDtoList.add(playerDto);
        }
    }
}
