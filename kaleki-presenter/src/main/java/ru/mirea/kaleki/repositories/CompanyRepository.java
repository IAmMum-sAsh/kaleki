package ru.mirea.kaleki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface Company repository.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * Find by id optional.
     *
     * @param company_id the company id
     * @return the optional
     */
    Optional<Company> findById(long company_id);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Company> findByName(String name);
}