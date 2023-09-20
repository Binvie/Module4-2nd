package com.example.playermanagement.controller;


import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import com.example.playermanagement.model.Position;
import com.example.playermanagement.service.IPlayerService;
import com.example.playermanagement.service.IPositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IPositionService positionService;


    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String name,
                           Model model) {
        Pageable pageable = PageRequest.of(page, 3);
        Page<IPlayerDto> playerDto = playerService.findAllPlayer(pageable, name);
        model.addAttribute("list", playerDto);
        model.addAttribute("searchName", name);
        return "/view";
    }

    @GetMapping("/delete")
    public String deletePlayer(@RequestParam int id, RedirectAttributes redirectAttributes) {
        playerService.remove(id);
        redirectAttributes.addFlashAttribute("mess", "Delete Successfully!");
        return "redirect:/player";
    }

    @GetMapping("/create")
    public String createPlayerForm(Model model) {
        PlayerDto playerDto = new PlayerDto();
        List<Position> positionList = positionService.findAll();
        model.addAttribute("playerDto", playerDto);
        model.addAttribute("list", positionList);
        return "/create";
    }

    @PostMapping("/create")
    public String createPlayer(@Validated PlayerDto playerDto, BindingResult bindingResult,
                               Model model) {
        playerDto.validate(playerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/create";
        }
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        player.setPosition(new Position(playerDto.getPositionId()));
        playerService.save(player);
        return "redirect:/player";

    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable int id, Model model) {
//        Player player = playerService.findPlayerById(id);
//        model.addAttribute("player", player);
        return "/edit";
    }

    @PostMapping("/edit")
    public String editPlayer(RedirectAttributes redirectAttributes, Player player) {
//        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Edit successfully!");
        return "redirect:/view";
    }
}
