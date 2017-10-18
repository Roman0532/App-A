/**
 * Created by Roman Maximov on 19.10.2017
 */
public class Validator {
    private String user_id;
    private String role;
    private String path;
    private String ds;
    private String de;
    private String volume;

    public Validator(String user_id, String role, String path, String ds, String de, String volume) {
        this.user_id = user_id;
        this.role = role;
        this.path = path;
        this.ds = ds;
        this.de = de;
        this.volume = volume;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
