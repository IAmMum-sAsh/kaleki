package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;

import java.util.List;
import java.util.Optional;

/**
 * The interface Project repository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /**
     * Find by id optional.
     *
     * @param project_id the project id
     * @return the optional
     */
    Optional<Project> findById(long project_id);

    /**
     * Find all by company list.
     *
     * @param company the company
     * @return the list
     */
    List<Optional<Project>> findAllByCompany(Company company);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Project> findByName(String name);
    Optional<Project> findById(Long id);
}