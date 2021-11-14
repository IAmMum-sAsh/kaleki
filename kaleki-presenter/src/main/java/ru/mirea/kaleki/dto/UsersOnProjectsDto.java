package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.security.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOnProjectsDto {
    protected UserDto user;
    protected ProjectDto project;
    protected String position;
    protected double rate;
    protected double base_salary;
}
