package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity{
    /**
     * The Username.
     */
    protected String username;
    /**
     * The Email.
     */
    protected String email;
    /**
     * The Password.
     */
    protected String password;
    /**
     * The Role.
     */
    protected String role;
    /**
     * The Company.
     */
    protected String company;
    /**
     * The Projects.
     */
    protected String projects;

}