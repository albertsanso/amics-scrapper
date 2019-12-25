package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="competitions")
public class JpaCompetition {
    private String id;
    private String name;
    private String groups;
    private List<JpaMatch> matches;

    public JpaCompetition() {}

    public JpaCompetition(String id, String name, String groups, List<JpaMatch> matches) {
        this.id = id;
        this.name = name;
        this.groups = groups;
        this.matches = matches;
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

    @OneToMany(
            mappedBy = "competition",
            cascade = CascadeType.ALL/*,
            orphanRemoval = true*/
    )
    public List<JpaMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<JpaMatch> matches) {
        this.matches = matches;
    }
}
