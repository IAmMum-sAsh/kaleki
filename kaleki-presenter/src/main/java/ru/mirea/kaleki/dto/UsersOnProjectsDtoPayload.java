package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOnProjectsDtoPayload {
    protected long user_id;
    protected long project_id;
    protected long position_id;
    protected double rate;
    protected double base_salary;
}
