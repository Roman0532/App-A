package domain;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public @Getter
@Setter
class User {
    @Expose
    private Long id;
    @Expose
    private String login;
    private String password;
    private String salt;

    public User(Long id, String login, String password, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}