package com.example.playermanagement.controller;


import com.example.playermanagement.model.Player;
import com.example.playermanagement.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("mess", "");
        return "/view";
    }

    @GetMapping("/delete")
    public String deletePlayer(@RequestParam int id, RedirectAttributes redirectAttributes){
        playerService.deletePlayer(id);
        redirectAttributes.addFlashAttribute("mess","Delete Successfully!");
        return "redirect:/player";
    }
    @GetMapping("/create")
    public String createPlayerForm(Model model){
        model.addAttribute("player", new Player());
        return "/create";
    }
    @PostMapping("/create")
    public String createPlayer(Player player, RedirectAttributes redirectAttributes){
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess","Create successfully!");
        return "redirect:/player";
    }
    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable int id, Model model){
        Player player = playerService.findPlayerById(id);
        model.addAttribute("player", player);
        return "/edit";
    }

    @PostMapping("/edit")
    public String editPlayer(RedirectAttributes redirectAttributes,Player player){
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess","Edit successfully!");
        return "redirect:/view";
    }
}
