package ru.mirea.kaleki.security.jwt;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.mirea.kaleki.entitys.User;


public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getEmail(),
                user.getPassword(),
                new SimpleGrantedAuthority(user.getRole()));
    }
}