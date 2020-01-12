package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="teams")
public class JpaTeam {
    private String id;
    private String name;
    private boolean isActive;
    private JpaOrganization organization;

    public JpaTeam() {}

    public JpaTeam(String id, String name, boolean isActive, JpaOrganization organization) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.organization = organization;
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

    @Column(name="active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    public JpaOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(JpaOrganization organization) {
        this.organization = organization;
    }
}
