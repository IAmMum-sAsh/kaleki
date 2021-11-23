package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The type Users on projects.
 */
@Entity
@Table(name = "users_on_projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(UsersOnProjectsPK.class)
public class UsersOnProjects {
    /**
     * The Users on projects pk.
     */
//    @Id
//    protected long user_id;
//    @Id
//    protected long project_id;
    @EmbeddedId
    protected UsersOnProjectsPK usersOnProjectsPK;

    /**
     * The Position.
     */
    @ManyToOne
    protected Position position;
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

    @Override
    public String toString() {
        return "UsersOnProjects{" +
                "usersOnProjectsPK=" + usersOnProjectsPK.toString() +
                ", position=" + position +
                ", rate=" + rate +
                ", base_salary=" + base_salary +
                ", week_work_time=" + week_work_time +
                '}';
    }
}