package service;

public class UserDataService {
    private String login;
    private String password;
    private String role;
    private String resource;
    private String dataStart;
    private String dataEnd;
    private String volume;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getResource() {
        return resource;
    }

    public String getDataStart() {
        return dataStart;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public String getVolume() {
        return volume;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setRole(String role) {
        this.role = role;
    }

    void setResource(String resource) {
        this.resource = resource;
    }

    void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * Нужна ли аутентификация
     */
    public boolean isAuthentication() {
        return this.login != null && this.password != null;
    }

    /**
     * Нужна ли авторизация
     */
    public boolean isAuthorization() {
        return isAuthentication() && this.resource != null && this.role != null;
    }

    /**
     * Нужен ли аккаунтинг
     */
    public boolean isAccounting() {
        return isAuthorization() && this.dataStart != null && this.dataEnd != null && this.volume != null;
    }
}