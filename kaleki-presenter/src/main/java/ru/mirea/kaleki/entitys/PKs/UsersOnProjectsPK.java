package ru.mirea.kaleki.entitys.PKs;

import lombok.Getter;
import lombok.Setter;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * The type Users on projects pk.
 */
@Embeddable
@Getter
@Setter
public class UsersOnProjectsPK implements Serializable {
    /**
     * The User.
     */
    @ManyToOne
    protected User user;
    /**
     * The Project.
     */
    @ManyToOne
    protected Project project;

    /**
     * Instantiates a new Users on projects pk.
     */
    public UsersOnProjectsPK() {}

    /**
     * Instantiates a new Users on projects pk.
     *
     * @param user    the user
     * @param project the project
     */
    public UsersOnProjectsPK(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    @Override
    public String toString() {
        return "UsersOnProjectsPK{" +
                "user=" + user +
                ", project=" + project +
                '}';
    }
}