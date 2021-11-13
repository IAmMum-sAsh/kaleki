package ru.mirea.kaleki.security.dto;

import lombok.Data;

@Data
public class JwtAuthDto {
    private String email;
    private String accessToken;
    private String refreshToken;
}
