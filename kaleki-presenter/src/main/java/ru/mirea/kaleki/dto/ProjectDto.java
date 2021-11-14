package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    protected long id;
    protected String name;
    protected CompanyDto company;
    protected Date start_date;
    protected String status;

    public ProjectDto(Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.company = new CompanyDto(project.getCompany());
        this.start_date = project.getStart_date();
        this.status = project.getStatus();
    }
}
