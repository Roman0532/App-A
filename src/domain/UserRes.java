package domain;

import java.util.ArrayList;

public class UserRes extends ArrayList<UserRes> {
    private Long id;
    private Long userId;
    private String path;
    private Roles role;

    public UserRes(Long id, Long userId, String path, Roles role) {
        this.id = id;
        this.userId = userId;
        this.path = path;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Roles getRole() {
        return role;
    }

    public String getRoleName()
    {
        return role.name();
    }
}
