package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_on_projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(UsersOnProjectsPK.class)
public class UsersOnProjects {
//    @Id
//    protected long user_id;
//    @Id
//    protected long project_id;
    @EmbeddedId
    protected UsersOnProjectsPK usersOnProjectsPK;

    @ManyToOne
    protected Position position;
    protected double rate;
    protected double base_salary;
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