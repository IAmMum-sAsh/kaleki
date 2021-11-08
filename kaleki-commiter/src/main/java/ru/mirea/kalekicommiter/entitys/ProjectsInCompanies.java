package ru.mirea.kalekicommiter.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "projects_in_companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsInCompanies extends BaseEntity{
    protected String username;
    protected String email;
    protected String password;
    protected String role;
}