package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.Position;

import java.util.Optional;

/**
 * The interface Position repository.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    /**
     * Find by id optional.
     *
     * @param position_id the position id
     * @return the optional
     */
    Optional<Position> findById(long position_id);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Position> findByName(String name);
}