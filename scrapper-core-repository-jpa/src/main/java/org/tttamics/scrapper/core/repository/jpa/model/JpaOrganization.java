package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="organizations")
public class JpaOrganization {
    private String id;
    private String name;
    private String type;
    private boolean isActive;
    private List<JpaMatch> matches;

    public JpaOrganization() {}

    public JpaOrganization(String id, String name, String type, boolean isActive, List<JpaMatch> matches) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isActive = isActive;
        this.matches = matches;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @OneToMany(
            mappedBy = "local",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<JpaMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<JpaMatch> matches) {
        this.matches = matches;
    }
}
