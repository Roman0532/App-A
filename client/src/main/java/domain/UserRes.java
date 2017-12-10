package domain;

import lombok.Getter;
import lombok.Setter;

public class UserRes {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private String path;
    @Getter
    @Setter
    private Roles role;

    public UserRes(Long id, Long userId, String path, Roles role) {
        this.id = id;
        this.userId = userId;
        this.path = path;
        this.role = role;
    }
}