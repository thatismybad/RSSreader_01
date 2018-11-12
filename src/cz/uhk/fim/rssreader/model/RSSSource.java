package cz.uhk.fim.rssreader.model;

public class RSSSource {

    private String name;
    private String source;

    public RSSSource() {
    }

    public RSSSource(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
