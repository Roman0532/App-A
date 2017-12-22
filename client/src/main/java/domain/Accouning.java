package domain;


import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

public @Getter
@Setter
class Accouning {
    private Long id;
    private String login;
    private String resource;
    @Expose
    private String dataStart;
    @Expose
    private String dataEnd;
    @Expose
    private String volume;

    public Accouning(String login, String resource, String dataStart,
                     String dataEnd, String volume) {
        this.login = login;
        this.resource = resource;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.volume = volume;
    }

    public Accouning(Long id, String dataStart, String dataEnd, String volume) {
        this.id = id;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.volume = volume;
    }
}