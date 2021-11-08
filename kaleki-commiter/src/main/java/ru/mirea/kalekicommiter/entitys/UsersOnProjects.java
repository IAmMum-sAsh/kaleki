package ru.mirea.kalekicommiter.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_on_projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersOnProjects extends BaseEntity{
    protected int user_id;
    protected int project_id;
    protected int position;
    protected double rate;
    protected double base_salary;
}