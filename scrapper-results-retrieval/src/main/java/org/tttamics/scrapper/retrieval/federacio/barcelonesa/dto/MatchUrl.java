package org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto;

public class MatchUrl {
    private int index;
    private int day;
    private String recordUrl;

    public MatchUrl(int day, String recordUrl, int index) {
        this.index = index;
        this.day = day;
        this.recordUrl = recordUrl;
    }

    public int getDay() {
        return day;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "MatchUrl{" +
                "index=" + index +
                ", day=" + day +
                ", recordUrl='" + recordUrl + '\'' +
                '}';
    }
}
