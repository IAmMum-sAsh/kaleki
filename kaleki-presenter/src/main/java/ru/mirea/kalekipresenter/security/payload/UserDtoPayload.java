package ru.mirea.kalekipresenter.security.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoPayload extends BasicPayload{
    protected String username;
    protected String email;
    protected String password;
    protected String role;

    public UserDtoPayload(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
