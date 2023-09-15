package com.example.playermanagement.controller;


import com.example.playermanagement.model.Player;
import com.example.playermanagement.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0",required = false)int page,
                           @RequestParam(defaultValue = "",required = false)String name,
                           Model model) {
        Pageable pageable = PageRequest.of(page,3, Sort.by("name").descending());
        model.addAttribute("list", playerService.findAll(pageable,name));
        model.addAttribute("searchName", name);
        return "/view";
    }

    @GetMapping("delete")
    public void deletePlayer(@RequestParam int id){
        playerService.deletePlayer(id);
    }
}
