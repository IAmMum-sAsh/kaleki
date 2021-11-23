package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.entitys.UsersOnProjects;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The interface Users on projects repository.
 */
@Repository
public interface UsersOnProjectsRepository extends JpaRepository<UsersOnProjects, Serializable> {
    /**
     * Find by users on projects pk optional.
     *
     * @param usersOnProjectsPK the users on projects pk
     * @return the optional
     */
    Optional<UsersOnProjects> findByUsersOnProjectsPK(UsersOnProjectsPK usersOnProjectsPK);
//    List<Optional<User>> findByProject(Project project);
//    List<Optional<Project>> findByUser(User user);
}