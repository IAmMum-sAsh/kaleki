package ru.mirea.kaleki.security.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mirea.kaleki.entitys.RefreshToken;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.RefreshTokenRepository;
import ru.mirea.kaleki.security.dto.JwtAuthDto;
import ru.mirea.kaleki.services.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenProvider {
    private Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long accessTokenExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExpiration;

    public JwtTokenProvider(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createAccessToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpiration);

        logger.info("Created access JWT for user " + user.toString());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken(long userID) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpiration);

        UUID randUUID = UUID.randomUUID();

        String createdToken = Jwts.builder()
                .setSubject(String.valueOf(randUUID))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

//        refreshTokenRepository.save(new RefreshToken(userID, createdToken));
        return createdToken;
    }

    public String resolveAccessToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateAccessToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public JwtAuthDto refreshPairOfTokens(String refreshTokenString) {
        if (!validateRefreshToken(refreshTokenString))
            throw new IllegalArgumentException("Refresh token is expired or invalid");

        UUID UUIDFromRefreshToken = UUID.fromString(Jwts.parser().setSigningKey(secret).parseClaimsJws(refreshTokenString).getBody().getSubject());

        RefreshToken currentRefreshToken = new RefreshToken();
//        RefreshToken currentRefreshToken = refreshTokenRepository.findById(refreshTokenString).orElseThrow(
//                                        () -> {throw new IllegalArgumentException("No such refresh token");}
//                                    );

        User subject = userService.findById(currentRefreshToken.getUser_id()).orElseThrow(IllegalArgumentException::new);

        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setEmail(subject.getEmail());
        jwtAuthDto.setAccessToken(createAccessToken(subject));
        jwtAuthDto.setRefreshToken(createRefreshToken(subject.getId()));

//        refreshTokenRepository.delete(currentRefreshToken);
//        refreshTokenRepository.save(new RefreshToken(currentRefreshToken.getUser_id(), jwtAuthDto.getRefreshToken()));

        return jwtAuthDto;
    }
}
