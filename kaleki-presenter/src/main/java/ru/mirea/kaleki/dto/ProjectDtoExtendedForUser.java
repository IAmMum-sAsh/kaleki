package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * The type Project dto extended for user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoExtendedForUser {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Company name.
     */
    protected String company_name;
    /**
     * The Ceo username.
     */
    protected String CEO_username;
    /**
     * The Ceo email.
     */
    protected String CEO_email;
    /**
     * The Start date.
     */
    protected Date start_date;
    /**
     * The Status.
     */
    protected String status;

    /**
     * The Position.
     */
    protected String position;
    /**
     * The Rate.
     */
    protected double rate;
    /**
     * The Base salary.
     */
    protected double base_salary;
    /**
     * The Week work time.
     */
    protected int week_work_time;
}
