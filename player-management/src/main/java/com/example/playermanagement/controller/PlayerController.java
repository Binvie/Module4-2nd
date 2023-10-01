package com.example.playermanagement.controller;


import com.example.playermanagement.dto.IPlayerDto;
import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.model.Player;
import com.example.playermanagement.model.Position;
import com.example.playermanagement.service.IPlayerService;
import com.example.playermanagement.service.IPositionService;
import com.example.playermanagement.service.ITeamService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private ITeamService teamService;

    @GetMapping("/player/detail/{id}")
    public String showDetailForm(@PathVariable int id, HttpServletResponse httpServletResponse, Model model) {
        Player player = playerService.findById(id).get();
        Cookie cookie = new Cookie("PlayerId", id +"");
        cookie.setMaxAge(30);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        model.addAttribute("title", "Overview");
        model.addAttribute("player", player );
        return "/detail";
    }

    @GetMapping("")
    public String showList(@CookieValue(defaultValue = "-1",required = false) int id ,
                            @RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String name,
                           Model model) {
        Player player = playerService.findById(id).get();
        Pageable pageable = PageRequest.of(page, 8);
        Page<Player> playerDto = playerService.findAllPlayer(pageable, name);
        model.addAttribute("list", playerDto);
        model.addAttribute("searchName", name);
        model.addAttribute("title", "Overview");
        model.addAttribute("teamList", teamService.findAll());
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
        model.addAttribute("teamList", teamService.findAll());
        model.addAttribute("positionList", positionList);
        return "/create";
    }

    @PostMapping("/create")
    public String createPlayer(@Valid PlayerDto playerDto, BindingResult bindingResult,
                               Model model) {
        new PlayerDto().validate(playerDto, bindingResult);
        List<Position> positionList = positionService.findAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("positionList", positionList);
            model.addAttribute("teamList", teamService.findAll());
            return "/create";
        }
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        playerService.save(player);
        return "redirect:/player";

    }

    @GetMapping("/edit/{id}")
    public String editPlayerForm(@PathVariable int id, Model model) {
        Player player = playerService.findById(id).get();
        PlayerDto playerDto = new PlayerDto();
        BeanUtils.copyProperties(player, playerDto);
        List<Position> list = positionService.findAll();
        model.addAttribute("playerDto", playerDto);
        model.addAttribute("teamList", teamService.findAll());
        model.addAttribute("list", list);
        model.addAttribute("title", "Edit Player");
        return "/edit";
    }

    @PostMapping("/edit")
    public String editPlayer(@Validated PlayerDto playerDto, RedirectAttributes redirectAttributes,
                             @RequestParam int id, BindingResult bindingResult, Model model) {
        new PlayerDto().validate(playerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            List<Position> list = positionService.findAll();
            model.addAttribute("list", list);
            model.addAttribute("teamList", teamService.findAll());
            return "/edit";
        }
        Player player = playerService.findById(id).get();
        BeanUtils.copyProperties(playerDto, player);
        playerService.save(player);
        redirectAttributes.addFlashAttribute("mess", "Edit successfully!");
        return "redirect:/player";
    }

    @GetMapping("/playing")
    public String setStatus(@RequestParam("id1") int id) {
        Player player = playerService.findById(id).get();
        if (player.getStatus() == 1) {
            player.setStatus(0);
            List<Player> list = playerService.findAll();
            list.set(id,player);
            return "redirect:/player";
        } else if (player.getStatus() == 0) {
            player.setStatus(1);
            return "redirect:/player";
        }
        return "redirect:/player";
    }

    @GetMapping("/error")
    public String showForm() {
        return "/404";
    }

    @GetMapping("/count")
    public String showCount(@PathVariable int id) {

        return "/view";
    }
}
