package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(long project_id);
    List<Optional<Project>> findAllByCompany(Company company);
}