package service;

public class UserData {
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

    @Override
    public String toString() {
        return "UserData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", resource='" + resource + '\'' +
                ", dataStart='" + dataStart + '\'' +
                ", dataEnd='" + dataEnd + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}