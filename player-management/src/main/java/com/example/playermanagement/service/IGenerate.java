package com.example.playermanagement.service;

import com.example.playermanagement.model.Player;

import java.util.List;
import java.util.Optional;

public interface IGenerate<T> {
    List<T> findAll();

    Optional<T> findById(int id);

    void save(T t);

    void remove(int id);

}
