package ru.mirea.kaleki.security.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User dto payload.
 */
@Data
@NoArgsConstructor
public class UserDtoPayload extends BasicPayload{
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
     * Instantiates a new User dto payload.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     */
    public UserDtoPayload(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
