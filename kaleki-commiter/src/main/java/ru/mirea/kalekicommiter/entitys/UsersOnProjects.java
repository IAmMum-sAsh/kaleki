package ru.mirea.kalekicommiter.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kalekicommiter.entitys.PKs.ProjectsInCompaniesPK;
import ru.mirea.kalekicommiter.entitys.PKs.UsersOnProjectsPK;

import javax.persistence.*;

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

    protected int position;
    protected double rate;
    protected double base_salary;
}