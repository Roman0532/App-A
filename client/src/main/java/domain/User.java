package domain;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String salt;

    public User(Long id, String login, String password, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}