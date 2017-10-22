/**
 * Created by Roman Maximov on 12.10.2017
 */
public class Resource {
    private Long id;
    private String path;

    public Resource(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    String getPath() {
        return path;
    }


}
