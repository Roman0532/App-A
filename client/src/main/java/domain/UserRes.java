package domain;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public @Getter
@Setter
class UserRes {
    @Expose
    private Long id;
    private Long userId;
    @Expose
    private String path;
    @Expose
    private Roles role;
    @Expose
    String roles;

    public UserRes(Long id, Long userId, String path, Roles role) {
        this.id = id;
        this.userId = userId;
        this.path = path;
        this.role = role;
    }

    public UserRes(Long id, String path, String roles) {
        this.id = id;
        this.path = path;
        this.roles = roles;
    }
}