package ru.mirea.kaleki.security.jwt;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.mirea.kaleki.entitys.User;


/**
 * The type Jwt user factory.
 */
public class JwtUserFactory {
    /**
     * Create jwt user.
     *
     * @param user the user
     * @return the jwt user
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getEmail(),
                user.getPassword(),
                new SimpleGrantedAuthority(user.getRole()));
    }
}