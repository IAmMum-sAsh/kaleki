package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.security.dto.UserDto;

/**
 * The type Users on projects dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOnProjectsDto {
    /**
     * The User.
     */
    protected UserDto user;
    /**
     * The Project.
     */
    protected ProjectDto project;
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
