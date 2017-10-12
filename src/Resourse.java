/**
 * Created by Roman Maximov on 12.10.2017
 */
public class Resourse {
    Long id;
    String path;

    public Resourse(Long id, String path) {

        this.id = id;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
