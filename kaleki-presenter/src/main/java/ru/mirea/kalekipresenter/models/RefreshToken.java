package ru.mirea.kalekipresenter.models;

public class RefreshToken {
    protected String refresh_token;
    protected long user_id;

    public RefreshToken(){}

    public RefreshToken(String refresh_token, long user_id) {
        this.refresh_token = refresh_token;
        this.user_id = user_id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "refresh_token='" + refresh_token + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
