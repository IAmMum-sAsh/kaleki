package ru.mirea.kaleki.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.User;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String username;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
