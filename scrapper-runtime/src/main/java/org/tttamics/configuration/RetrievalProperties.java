package org.tttamics.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("retrieval")
public class RetrievalProperties {
    private String bcn;
    private String cat;
    private String esp;

    public String getBcn() {
        return bcn;
    }

    public void setBcn(String bcn) {
        this.bcn = bcn;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }
}
