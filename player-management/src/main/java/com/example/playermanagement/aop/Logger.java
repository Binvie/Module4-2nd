package com.example.playermanagement.aop;

import com.example.playermanagement.dto.PlayerDto;
import com.example.playermanagement.repository.IPlayerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @Autowired
    private IPlayerRepository playerRepository;
    private static  int count = 0;
    @AfterReturning("execution(* com.example.playermanagement.controller.PlayerController.createPlayer(..))")
    public void countPlayer(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        PlayerDto playerDto = (PlayerDto) args[0];
        int count = playerRepository.countPlayer(playerDto.getTeam().getName());
        System.out.println(count);
    }
}
