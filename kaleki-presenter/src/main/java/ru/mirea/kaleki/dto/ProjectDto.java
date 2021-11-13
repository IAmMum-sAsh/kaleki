package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;

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
}
