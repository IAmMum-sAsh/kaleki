package ru.mirea.kaleki.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Refresh token.
 */
@Entity
@Table(name = "refresh_tokens")
@Data
@NoArgsConstructor
public class RefreshToken {
    /**
     * The Refresh token.
     */
    @Id
    @Column(name = "refresh_token")
    protected String refresh_token;

    /**
     * The User id.
     */
    @Column(name = "user_id")
    protected long user_id;

    /**
     * Instantiates a new Refresh token.
     *
     * @param userId       the user id
     * @param refreshToken the refresh token
     */
    public RefreshToken(long userId, String refreshToken) {
        this.user_id = userId;
        this.refresh_token = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "refresh_token='" + refresh_token + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}