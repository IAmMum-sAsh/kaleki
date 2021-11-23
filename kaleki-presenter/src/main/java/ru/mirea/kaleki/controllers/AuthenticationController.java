package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.security.dto.AuthenticationRequestDto;
import ru.mirea.kaleki.security.dto.JwtAuthDto;
import ru.mirea.kaleki.security.exeption.JwtInvalidRefreshTokenException;
import ru.mirea.kaleki.security.jwt.JwtTokenProvider;
import ru.mirea.kaleki.services.UserService;


/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Login response entity.
     *
     * @param authenticationRequestDto the authentication request dto
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            User user = userService.findByEmail(authenticationRequestDto.getEmail()).orElseThrow(IllegalArgumentException::new);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), authenticationRequestDto.getPassword()));

            String accessToken = jwtTokenProvider.createAccessToken(user);
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

            JwtAuthDto jwtAuthDto = new JwtAuthDto();
            jwtAuthDto.setEmail(user.getEmail());
            jwtAuthDto.setAccessToken(accessToken);
            jwtAuthDto.setRefreshToken(refreshToken);

            return ResponseEntity.ok(jwtAuthDto);
        }
        catch (IllegalArgumentException e) {
            throw new JwtInvalidRefreshTokenException();
        }
    }

    /**
     * Refresh response entity.
     *
     * @param tokenPairToRefresh the token pair to refresh
     * @return the response entity
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthDto> refresh(@RequestBody JwtAuthDto tokenPairToRefresh) {
        JwtAuthDto jwtAuthDto = jwtTokenProvider.refreshPairOfTokens(tokenPairToRefresh.getRefreshToken());
        return ResponseEntity.ok(jwtAuthDto);
    }

}