package classes;

public class UserRes {
    private Long id;
    private Long user_id;
    private String path;
    private String role;

    public UserRes(Long id, Long user_id, String path, String role) {
        this.id = id;
        this.user_id = user_id;
        this.path = path;
        this.role = role;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getRole() {
        return role;
    }

}
