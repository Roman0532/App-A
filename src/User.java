/**
 * Created by Roman Maximov on 12.10.2017
 */
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

    public String getSalt() {
        return salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() { //password = Main.hashPass(Main.hashPass(getPassword())+ getSalt());
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

