package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.CompetitionCreatedEvent;
import org.tttamics.scrapper.core.domain.event.CompetitionGroupAddedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Competition extends Entity {
    private String id;
    private String name;
    private List<CompetitionGroup> groups;

    private Competition(String id, String name, List<CompetitionGroup> groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
    }

    private static Competition createNewCompetition(CompetitionBuilder builder) {
        List<CompetitionGroup> groups = builder.getGroups();
        if (groups == null) {
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

    public static CompetitionBuilder builder(String id, String name) {
        return new CompetitionBuilder(id, name);
    }

    public void addCompetitionGroup(CompetitionGroup group) {
        this.groups.add(group);

        this.publishEvent(new CompetitionGroupAddedEvent());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CompetitionGroup> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public static final class CompetitionBuilder {
        private String id;
        private String name;
        private List<CompetitionGroup> groups;

        public CompetitionBuilder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public CompetitionBuilder withId(String id) {
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

        public String getId() {
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
