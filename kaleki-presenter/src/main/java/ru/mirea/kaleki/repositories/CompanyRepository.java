package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findById(long company_id);
    Optional<Company> findByName(String name);
}