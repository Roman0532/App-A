package domain;


import lombok.Getter;
import lombok.Setter;


public class Accouning {

    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String resource;
    @Getter
    @Setter
    private String dataStart;
    @Getter
    @Setter
    private String dataEnd;
    @Getter
    @Setter
    private String volume;

    public Accouning(String login, String resource, String dataStart,
                     String dataEnd, String volume) {
        this.login = login;
        this.resource = resource;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.volume = volume;
    }
}