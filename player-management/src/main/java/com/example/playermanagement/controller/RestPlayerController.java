package com.example.playermanagement.controller;

import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import com.example.playermanagement.service.IPlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/players")
@CrossOrigin("*")
public class RestPlayerController {
    @Autowired
    private IPlayerService iPlayerService ;

    @GetMapping("")
    public ResponseEntity<Page<Player>> showList(@RequestParam(defaultValue = "0",required = false) int page,
                                                    @RequestParam(defaultValue = "",required = false)String name){
        Pageable pageable = PageRequest.of(page,2);
        Page<Player> playerDtoPage = iPlayerService.findAllPlayer(pageable,name);
        if (playerDtoPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(playerDtoPage,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody PlayerDto playerDto){
        if (playerDto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = new Player();
        BeanUtils.copyProperties(playerDto,player);
        iPlayerService.save(player);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
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
