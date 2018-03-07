package org.voiddog.spring.test.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUserRequest {

    @NotNull
    @Size(min = 2, max = 20)
    private String account;

    private String name;

    @NotNull
    private String password;

    private String avatar;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
