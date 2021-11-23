package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Users on projects dto payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOnProjectsDtoPayload {
    /**
     * The User id.
     */
    protected long user_id;
    /**
     * The Project id.
     */
    protected long project_id;
    /**
     * The Position id.
     */
    protected long position_id;
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
