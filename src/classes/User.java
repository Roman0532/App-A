package classes;

public class User {
    private Long id;
    private String login;
    private String password;
    private String salt;

    public User(Long id, String login, String password, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() { //password = Main.hashPass(Main.hashPass(getPassword())+ getSalt());
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Long getId() {
        return id;
    }
}