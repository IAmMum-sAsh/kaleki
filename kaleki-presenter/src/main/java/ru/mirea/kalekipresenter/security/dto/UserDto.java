package ru.mirea.kalekipresenter.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kalekipresenter.models.User;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String role;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
