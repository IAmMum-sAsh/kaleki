package ru.mirea.kaleki.entitys.PKs;

import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UsersOnProjectsPK implements Serializable {
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Project project;

    public UsersOnProjectsPK() {}

    public UsersOnProjectsPK(User user, Project project) {
        this.user = user;
        this.project = project;
    }
}