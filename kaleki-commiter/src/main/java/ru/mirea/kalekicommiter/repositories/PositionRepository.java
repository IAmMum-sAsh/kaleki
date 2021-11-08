package ru.mirea.kalekicommiter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kalekicommiter.entitys.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findById(int position_id);
    Optional<Position> findByName(String name);
}