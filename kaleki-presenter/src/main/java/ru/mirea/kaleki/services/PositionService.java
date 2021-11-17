package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.entitys.Position;
import ru.mirea.kaleki.repositories.PositionRepository;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    protected PositionRepository positionRepository;

    public Position findById(long id){
        return positionRepository.findById(id).get();
    }

    public List<Position> findAll(){
        return positionRepository.findAll();
    }
}
