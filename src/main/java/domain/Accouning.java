package domain;

public class Accouning {
    private String login;
    private String resource;
    private String dataStart;
    private String dataEnd;
    private String volume;

    public Accouning(String login, String resource, String dataStart,
                     String dataEnd, String volume) {
        this.login = login;
        this.resource = resource;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.volume = volume;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}