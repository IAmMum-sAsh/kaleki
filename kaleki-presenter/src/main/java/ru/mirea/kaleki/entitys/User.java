package ru.mirea.kaleki.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity{
    protected String username;
    protected String email;
    protected String password;
    protected String role;
    protected String company;
    protected String projects;

}