/**
 * Created by Roman Maximov on 12.10.2017
 */
public class UserRes {
    Long id;
    Long user_id;
    Long res_id;
    String role;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRes_id() {
        return res_id;
    }

    public void setRes_id(Long res_id) {
        this.res_id = res_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
