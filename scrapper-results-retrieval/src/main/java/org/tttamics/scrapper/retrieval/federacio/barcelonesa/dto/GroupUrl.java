package org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto;

public class GroupUrl {
    private String groupName;
    private String url;

    public GroupUrl(String groupName, String url) {
        this.groupName = groupName;
        this.url = url;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "GroupUrl{" +
                "groupName='" + groupName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
