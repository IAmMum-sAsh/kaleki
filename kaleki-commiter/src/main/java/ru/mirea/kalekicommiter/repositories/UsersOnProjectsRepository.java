package ru.mirea.kalekicommiter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kalekicommiter.entitys.UsersOnProjects;

import java.util.Optional;

@Repository
public interface UsersOnProjectsRepository extends JpaRepository<UsersOnProjects, Integer> {
    Optional<UsersOnProjects> findByUser_idAndProject_id(int user_id, int project_id);
}