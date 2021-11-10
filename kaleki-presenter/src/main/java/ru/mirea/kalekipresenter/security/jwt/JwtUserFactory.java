package ru.mirea.kalekipresenter.security.jwt;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.mirea.kalekipresenter.models.User;


public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getEmail(),
                user.getPassword(),
                new SimpleGrantedAuthority(user.getRole()));
    }
}