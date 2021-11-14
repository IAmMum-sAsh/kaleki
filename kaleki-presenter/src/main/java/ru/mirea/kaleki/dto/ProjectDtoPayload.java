package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoPayload {
    protected String name;
    protected Long company;
    protected Date start_date;
    protected String status;
}
