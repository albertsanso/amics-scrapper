package org.tttamics.scrapper.retrieval.federacio.catalana.nacional3a.dto;

public class DayResults {
    private String pdfUrl;
    private Integer day;
    private String dayAsLabel;

    public DayResults(String pdfUrl, Integer day, String dayAsLabel) {
        this.pdfUrl = pdfUrl;
        this.day = day;
        this.dayAsLabel = dayAsLabel;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public Integer getDay() {
        return day;
    }

    public String getDayAsLabel() {
        return dayAsLabel;
    }

    @Override
    public String toString() {
        return "DayResults{" +
                "pdfUrl='" + pdfUrl + '\'' +
                ", day=" + day +
                ", dayAsLabel='" + dayAsLabel + '\'' +
                '}';
    }
}
