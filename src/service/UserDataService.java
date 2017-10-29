package service;

import static service.ParseService.cmd;

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
     * Нужна ли справка
     */
    public static boolean isHelp() {
        return cmd.hasOption("help") || cmd.hasOption("h") || !isAuthentication();
    }

    /**
     * Нужна ли аутентификация
     */
    public static boolean isAuthentication() {
        return cmd.hasOption("login") && cmd.hasOption("password");
    }

    /**
     * Нужна ли авторизация
     */
    public static boolean isAuthorization() {
        return isAuthentication() && cmd.hasOption("resource") && cmd.hasOption("role");
    }

    /**
     * Нужен ли аккаунтинг
     */
    public static boolean isAccounting() {
        return isAuthorization() && cmd.hasOption("dataStart") && cmd.hasOption("dataEnd") && cmd.hasOption("volume");
    }
}