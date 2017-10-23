public class UserRes {
    private Long id;
    private Long user_id;
    //private Long res_id;
    private String path;
    private String role;

    public UserRes(Long id, Long user_id ,String path, String role) {
        this.id = id;/*Long res_id*/
        this.user_id = user_id;
        //this.res_id = res_id;
        this.path = path;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    Long getUser_id() { return user_id; }

    public String getPath() {
        return path;
    }
    // Long getRes_id() { return res_id; }

    String getRole() {
        return role;
    }
}
