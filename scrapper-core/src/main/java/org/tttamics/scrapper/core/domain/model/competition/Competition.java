package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.CompetitionCreatedEvent;
import org.tttamics.scrapper.core.domain.event.CompetitionGroupAddedEvent;
import org.tttamics.scrapper.core.domain.event.CompetitionModifiedEvent;
import org.tttamics.scrapper.core.domain.model.match.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Competition extends Entity {
    private String id;
    private String name;
    private List<CompetitionGroup> groups;
    private List<Match> matches;

    private Competition(String id, String name, List<CompetitionGroup> groups, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.groups = groups;
        this.matches = matches;
    }

    private static Competition createNewCompetition(CompetitionBuilder builder) {
        List<CompetitionGroup> groups = builder.getGroups();
        if (Objects.isNull(groups)) {
            groups = new ArrayList<>();
        }

        List<Match> matches = builder.getMatches();
        if (Objects.isNull(matches)) {
            matches = new ArrayList<>();
        }

        Competition competition = new Competition(
            builder.getId(),
            builder.getName(),
            groups,
            matches
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CompetitionGroup> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    public List<Match> getMatches() { return Collections.unmodifiableList(matches); }

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

    public static final class CompetitionBuilder {
        private String id;
        private String name;
        private List<CompetitionGroup> groups;
        private List<Match> matches;

        public CompetitionBuilder(String name) {
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

        public CompetitionBuilder withMatches(List<Match> matches) {
            this.matches = matches;
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

        public List<Match> getMatches() { return matches; }

        public Competition create() { return createNewCompetition(this); }
    }
}
