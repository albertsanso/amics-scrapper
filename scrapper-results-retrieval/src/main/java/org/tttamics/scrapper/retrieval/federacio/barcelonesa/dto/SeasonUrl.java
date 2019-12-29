package org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto;

public class SeasonUrl {
    private String season;
    private String url;

    public SeasonUrl(String season, String url) {
        this.season = season;
        this.url = url;
    }

    public String getSeason() {
        return season;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "SeasonUrl{" +
                "season='" + season + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
