/**
 * UserRes
 * 1.0
 * Created by Roman Maximov
 * 12.10.2017
 */
public class UserRes {
    private Long id;
    private Long user_id;
    private Long res_id;
    private String role;

    public UserRes(Long id, Long user_id, Long res_id, String role) {
        this.id = id;
        this.user_id = user_id;
        this.res_id = res_id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long getUser_id() { return user_id; }

    Long getRes_id() { return res_id; }

    String getRole() {
        return role;
    }
}
