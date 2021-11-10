package ru.mirea.kalekicommiter.entitys.PKs;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsersOnProjectsPK implements Serializable {
    protected Long user_id;
    protected Long project_id;

    public UsersOnProjectsPK() {}

    public UsersOnProjectsPK(Long user_id, Long project_id) {
        this.user_id = user_id;
        this.project_id = project_id;
    }
}