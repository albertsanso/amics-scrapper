package org.tttamics.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("retrieval")
public class RetrievalProperties {
    private RetrievalBcnProperties bcn;
    private String cat;
    private String esp;

    public RetrievalBcnProperties getBcn() {
        return bcn;
    }

    public void setBcn(RetrievalBcnProperties bcn) {
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
