package org.tttamics.scrapper.core.repository.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="matches")
public class JpaMatch {
    private String id;
    private JpaOrganization local;
    private JpaOrganization visitor;
    private String startDateTime;
    private String group;
    private Integer day;
    private Integer localResultValue;
    private Integer visitorResultValue;

    public JpaMatch() {}

    public JpaMatch(String id, JpaOrganization local, JpaOrganization visitor, String startDateTime, String group, Integer day, Integer localResultValue, Integer visitorResultValue) {
        this.id = id;
        this.local = local;
        this.visitor = visitor;
        this.startDateTime = startDateTime;
        this.group = group;
        this.day = day;
        this.localResultValue = localResultValue;
        this.visitorResultValue = visitorResultValue;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name="local_organization_id")
    public JpaOrganization getLocal() {
        return local;
    }

    public void setLocal(JpaOrganization local) {
        this.local = local;
    }

    @OneToOne
    @JoinColumn(name="visitor_organization_id")
    public JpaOrganization getVisitor() {
        return visitor;
    }

    public void setVisitor(JpaOrganization visitor) {
        this.visitor = visitor;
    }

    @Column(name="start_datetime")
    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @Column(name="day")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Column(name="local_result")
    public Integer getLocalResultValue() {
        return localResultValue;
    }

    public void setLocalResultValue(Integer localResultValue) {
        this.localResultValue = localResultValue;
    }

    @Column(name="visitor_result")
    public Integer getVisitorResultValue() {
        return visitorResultValue;
    }

    public void setVisitorResultValue(Integer visitorResultValue) {
        this.visitorResultValue = visitorResultValue;
    }

    @Column(name="competitiongroup")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
