package com.example.playermanagement.service;

import com.example.playermanagement.model.Position;
import com.example.playermanagement.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PositionService implements IPositionService {
@Autowired
private IPositionRepository positionRepository;
    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(int id) {
        return positionRepository.findById(id);
    }

    @Override
    public void save(Position position) {

    }
    @Override
    public void remove(int id) {

    }
}
