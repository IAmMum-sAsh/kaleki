package ru.mirea.kalekicommiter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kalekicommiter.entitys.PKs.UsersOnProjectsPK;
import ru.mirea.kalekicommiter.entitys.UsersOnProjects;

import java.util.Optional;

@Repository
public interface UsersOnProjectsRepository extends JpaRepository<UsersOnProjects, Long> {
    Optional<UsersOnProjects> findByUsersOnProjectsPK(UsersOnProjectsPK usersOnProjectsPK);
}