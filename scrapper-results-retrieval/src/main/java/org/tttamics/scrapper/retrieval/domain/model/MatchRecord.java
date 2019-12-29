package org.tttamics.scrapper.retrieval.domain.model;

import java.time.ZonedDateTime;

public class MatchRecord {
    private String category;
    private String group;
    private String season;
    private int day;
    private ZonedDateTime dateTime;
    private String local;
    private String visitor;
    private int localScore;
    private int visitorScore;
    private int localGamesWon;
    private int visitorGamesWon;
    private String observations;

    public MatchRecord(String category, String group, String season, int day, ZonedDateTime dateTime, String local, String visitor, int localScore, int visitorScore, int localGamesWon, int visitorGamesWon, String observations) {
        this.category = category;
        this.group = group;
        this.season = season;
        this.day = day;
        this.dateTime = dateTime;
        this.local = local;
        this.visitor = visitor;
        this.localScore = localScore;
        this.visitorScore = visitorScore;
        this.localGamesWon = localGamesWon;
        this.visitorGamesWon = visitorGamesWon;
        this.observations = observations;
    }

    public String getCategory() {
        return category;
    }

    public String getGroup() {
        return group;
    }

    public String getSeason() {
        return season;
    }

    public int getDay() {
        return day;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public String getLocal() {
        return local;
    }

    public String getVisitor() {
        return visitor;
    }

    public int getLocalScore() {
        return localScore;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    public int getLocalGamesWon() {
        return localGamesWon;
    }

    public int getVisitorGamesWon() {
        return visitorGamesWon;
    }

    public String getObservations() {
        return observations;
    }

    @Override
    public String toString() {
        return "MatchRecord{" +
                "category='" + category + '\'' +
                ", group='" + group + '\'' +
                ", season='" + season + '\'' +
                ", day=" + day +
                ", dateTime=" + dateTime +
                ", local='" + local + '\'' +
                ", visitor='" + visitor + '\'' +
                ", localScore=" + localScore +
                ", visitorScore=" + visitorScore +
                ", localGamesWon=" + localGamesWon +
                ", visitorGamesWon=" + visitorGamesWon +
                ", observations='" + observations + '\'' +
                '}';
    }

    public String[] toArray() {
        String[] arr = new String[12];
        arr[0] = category;
        arr[1] = group;
        arr[2] = season;
        arr[3] = ""+day;
        arr[4] = ""+dateTime;
        arr[5] = local;
        arr[6] = visitor;
        arr[7] = ""+localScore;
        arr[8] = ""+visitorScore;
        arr[9] = ""+localGamesWon;
        arr[10] = ""+visitorGamesWon;
        arr[11] = ""+observations;
        return arr;
    }


}
