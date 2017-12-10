package service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString()
public class UserData {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private String resource;
    @Getter
    @Setter
    private String dataStart;
    @Getter
    @Setter
    private String dataEnd;
    @Getter
    @Setter
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