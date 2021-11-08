package ru.mirea.kalekicommiter.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity{
    protected String name;
    protected int company_id;
    protected Date start_date;
    protected Date finish_date;
    protected String status;
}