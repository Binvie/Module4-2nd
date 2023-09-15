package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @GetMapping("")
    public String showList(Model model) {
        List<Player> playerList = playerService.findAll();
        model.addAttribute("list", playerList);
        return "view";
    }
}
