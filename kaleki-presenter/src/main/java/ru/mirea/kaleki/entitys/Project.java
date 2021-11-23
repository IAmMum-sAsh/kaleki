package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

/**
 * The type Project.
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity{
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Company.
     */
    @ManyToOne
    protected Company company;
    /**
     * The Start date.
     */
    protected Date start_date;
    /**
     * The Status.
     */
    protected String status;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", start_date=" + start_date +
                ", status='" + status + '\'' +
                '}';
    }
}