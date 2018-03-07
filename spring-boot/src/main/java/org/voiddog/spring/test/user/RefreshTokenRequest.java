package org.voiddog.spring.test.user;

import javax.validation.constraints.NotNull;

public class RefreshTokenRequest {

    @NotNull
    private Long id;

    @NotNull
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
