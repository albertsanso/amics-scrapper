package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organizations")
public class JpaOrganization {
    private String id;
    private String name;
    private String type;

    public JpaOrganization() {
    }

    public JpaOrganization(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
}
