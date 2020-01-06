package org.tttamics.configuration;

import java.util.List;

public class RetrievalBcnProperties {

    private String file;

    private List<String> seasons;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }
}
