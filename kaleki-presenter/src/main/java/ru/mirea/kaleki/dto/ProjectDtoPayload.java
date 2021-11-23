package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;

import java.sql.Date;

/**
 * The type Project dto payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoPayload {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Company.
     */
    protected Long company;
    /**
     * The Start date.
     */
    protected Date start_date;
    /**
     * The Status.
     */
    protected String status;
    /**
     * The Salary.
     */
    protected double salary;
}
