package com.example.playermanagement.controller;

import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import com.example.playermanagement.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.oracle.util.Checksums.update;
@CrossOrigin("*")
@RestController
@RequestMapping("api/players")
public class RestPlayerController {
    @Autowired
    private IPlayerService iPlayerService ;

    @GetMapping()
    public ResponseEntity<Player> showList(){
        List<Player> playerDtosList = iPlayerService.findAll();
        if (playerDtosList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Player> Update(@PathVariable int id, @RequestBody PlayerDto playerDto){
        Player player = iPlayerService.findById(id).get();
        if (player == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(playerDto,player);
        iPlayerService.save(player);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
