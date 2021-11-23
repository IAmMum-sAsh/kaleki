package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;

import java.sql.Date;

/**
 * The type Project dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Company.
     */
    protected CompanyDto company;
    /**
     * The Start date.
     */
    protected Date start_date;
    /**
     * The Status.
     */
    protected String status;

    /**
     * Instantiates a new Project dto.
     *
     * @param project the project
     */
    public ProjectDto(Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.company = new CompanyDto(project.getCompany());
        this.start_date = project.getStart_date();
        this.status = project.getStatus();
    }
}
