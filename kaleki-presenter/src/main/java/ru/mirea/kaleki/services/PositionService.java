package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.entitys.Position;
import ru.mirea.kaleki.repositories.PositionRepository;

import java.util.List;

/**
 * The type Position service.
 */
@Service
public class PositionService {
    /**
     * The Position repository.
     */
    @Autowired
    protected PositionRepository positionRepository;

    /**
     * Find by id position.
     *
     * @param id the id
     * @return the position
     */
    public Position findById(long id){
        return positionRepository.findById(id).get();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Position> findAll(){
        return positionRepository.findAll();
    }
}
