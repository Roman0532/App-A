package service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString()
public @Getter
@Setter
class UserData {
    private String login;
    private String password;
    private String role;
    private String resource;
    private String dataStart;
    private String dataEnd;
    private String volume;

    /**
     * Нужно ли выполнить аутентификацию
     */
    public boolean isAuthentication() {
        return this.login != null && this.password != null;
    }

    /**
     * Нужно ли выполнить авторизация
     */
    public boolean isAuthorization() {
        return isAuthentication() && this.resource != null && this.role != null;
    }

    /**
     * Нужно ли выполнить аккаунтинг
     */
    public boolean isAccounting() {
        return isAuthorization() && this.dataStart != null
                && this.dataEnd != null && this.volume != null;
    }
}