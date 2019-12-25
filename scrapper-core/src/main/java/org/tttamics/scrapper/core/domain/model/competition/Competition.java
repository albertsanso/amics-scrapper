package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.CompetitionCreatedEvent;
import org.tttamics.scrapper.core.domain.event.CompetitionGroupAddedEvent;
import org.tttamics.scrapper.core.domain.event.CompetitionModifiedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Competition extends Entity {
    private CompetitionId id;
    private String name;
    private List<CompetitionGroup> groups;

    private Competition(CompetitionId id, String name, List<CompetitionGroup> groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
    }

    private static Competition createNewCompetition(CompetitionBuilder builder) {
        List<CompetitionGroup> groups = builder.getGroups();
        if (Objects.isNull(groups)) {
            groups = new ArrayList<>();
        }

        Competition competition = new Competition(
            builder.getId(),
            builder.getName(),
            groups
        );

        competition.initCompetitionCreatedEvent();

        return competition;
    }

    private void initCompetitionCreatedEvent() {
        publishEvent(new CompetitionCreatedEvent());
    }

    public static CompetitionBuilder builder(String name) {
        return new CompetitionBuilder(name);
    }

    public void addCompetitionGroup(CompetitionGroup group) {
        this.groups.add(group);

        this.publishEvent(new CompetitionGroupAddedEvent());
    }

    public CompetitionId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CompetitionGroup> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public void modifyCompetition(String name, List<CompetitionGroup> groups) {
        this.name = name;
        this.groups = groups;

        publishEvent(new CompetitionModifiedEvent());
    }

    public boolean isGroupInCompetition(CompetitionGroup competitionGroup) {
        return this.groups.stream()
                .map(CompetitionGroup::getName)
                .anyMatch(competitionGroup.getName()::equals);
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groups=" + groups +
                '}';
    }

    public static final class CompetitionBuilder {
        private CompetitionId id;
        private String name;
        private List<CompetitionGroup> groups;

        public CompetitionBuilder(String name) {
            this.name = name;
        }

        public CompetitionBuilder withId(CompetitionId id) {
            this.id = id;
            return this;
        }

        public CompetitionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CompetitionBuilder withGroups(List<CompetitionGroup> groups) {
            this.groups = groups;
            return this;
        }

        public CompetitionId getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<CompetitionGroup> getGroups() {
            return groups;
        }

        public Competition create() { return createNewCompetition(this); }
    }
}
