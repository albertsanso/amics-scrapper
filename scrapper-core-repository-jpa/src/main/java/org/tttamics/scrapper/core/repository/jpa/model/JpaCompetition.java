package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competitions")
public class JpaCompetition {
    private String id;
    private String name;
    private String groups;

    public JpaCompetition() {}

    public JpaCompetition(String id, String name, String groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
    }

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "competitiongroups")
    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
