package ru.mirea.kalekicommiter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kalekicommiter.entitys.PKs.ProjectsInCompaniesPK;
import ru.mirea.kalekicommiter.entitys.ProjectsInCompanies;

import java.util.Optional;

@Repository
public interface ProjectsInCompaniesRepository extends JpaRepository<ProjectsInCompanies, Long> {
    Optional<ProjectsInCompanies> findByProjectsInCompaniesPK (ProjectsInCompaniesPK projectsInCompaniesPK);
}