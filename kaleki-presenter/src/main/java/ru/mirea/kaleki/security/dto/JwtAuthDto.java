package ru.mirea.kaleki.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Jwt auth dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthDto {
    private String email;
    private String accessToken;
    private String refreshToken;
}
