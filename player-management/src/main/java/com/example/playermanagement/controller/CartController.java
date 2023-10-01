package com.example.playermanagement.controller;

import com.example.playermanagement.dto.CartDto;
import com.example.playermanagement.dto.PlayerDto;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")

public class CartController {
    @ModelAttribute("cart")
    public CartDto initCart(){
        return new CartDto();
    }

    @GetMapping
    public ModelAndView showCart(@SessionAttribute(value = "cart",required = false)PlayerDto playerDto){
        return new  ModelAndView("cart/list","cart",playerDto);
    }
}
