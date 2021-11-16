package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoExtended {
    protected long id;
    protected String name;
    protected CompanyDto company;
    protected Date start_date;
    protected String status;

    protected String position;
    protected double rate;
    protected double base_salary;
    protected int week_work_time;
}
