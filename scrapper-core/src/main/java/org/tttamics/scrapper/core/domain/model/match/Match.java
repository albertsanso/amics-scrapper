package org.tttamics.scrapper.core.domain.model.match;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.MatchCreatedEvent;
import org.tttamics.scrapper.core.domain.event.MatchModifiedEvent;
import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.organization.Organization;

import java.time.ZonedDateTime;

public class Match extends Entity {
    private String id;
    private Organization local;
    private Organization visitor;
    private ZonedDateTime startDateTime;
    private Competition competition;
    private CompetitionGroup group;
    private Integer day;
    private MatchResult result;

    private Match(String id,
                  Organization local,
                  Organization visitor,
                  ZonedDateTime startDateTime,
                  Competition competition,
                  CompetitionGroup group,
                  Integer day,
                  MatchResult result) {
        this.id = id;
        this.local = local;
        this.visitor = visitor;
        this.startDateTime = startDateTime;
        this.competition = competition;
        this.group = group;
        this.day = day;
        this.result = result;
    }

    private static Match createNewMatch(MatchBuilder builder) {
        Match match = new Match(
                builder.getId(),
                builder.getLocal(),
                builder.getVisitor(),
                builder.getStartDateTime(),
                builder.getCompetition(),
                builder.getGroup(),
                builder.getDay(),
                builder.getResult()
        );

        match.initMatchCreatedEvent();

        return match;
    }

    private void initMatchCreatedEvent() {
        publishEvent(new MatchCreatedEvent());
    }

    public static MatchBuilder builder(Competition competition, Organization local, Organization visitor) { return new MatchBuilder(competition, local, visitor); }

    public String getId() {
        return id;
    }

    public Organization getLocal() {
        return local;
    }

    public Organization getVisitor() {
        return visitor;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public CompetitionGroup getGroup() {
        return group;
    }

    public Integer getDay() {
        return day;
    }

    public MatchResult getResult() {
        return result;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void modifyMatch(
            Organization local,
            Organization visitor,
            ZonedDateTime startDateTime,
            Competition competition,
            CompetitionGroup group,
            Integer day,
            MatchResult result) {

        this.local = local;
        this.visitor = visitor;
        this.startDateTime = startDateTime;
        this.competition = competition;
        this.group = group;
        this.day = day;
        this.result = result;

        publishEvent(new MatchModifiedEvent());
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", local=" + local +
                ", visitor=" + visitor +
                ", startDateTime=" + startDateTime +
                ", competition=" + competition +
                ", group=" + group +
                ", day=" + day +
                ", result=" + result +
                '}';
    }

    public static final class MatchBuilder {
        private String id;
        private Organization local;
        private Organization visitor;
        private ZonedDateTime startDateTime;
        private Competition competition;
        private CompetitionGroup group;
        private Integer day;
        private MatchResult result;

        public MatchBuilder(Competition competition, Organization local, Organization visitor) {
            this.competition = competition;
            this.local = local;
            this.visitor = visitor;
        }

        public MatchBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public MatchBuilder withCompetition(Competition competition) {
            this.competition = competition;
            return this;
        }

        public MatchBuilder withLocal(Organization local) {
            this.local = local;
            return this;
        }

        public MatchBuilder withVisitor(Organization visitor) {
            this.visitor = visitor;
            return this;
        }

        public MatchBuilder withStartDateTime(ZonedDateTime startDateTime) {
            this.startDateTime = startDateTime;
            return this;
        }

        public MatchBuilder withGroup(CompetitionGroup group) {
            this.group = group;
            return this;
        }

        public MatchBuilder withDay(Integer day) {
            this.day = day;
            return this;
        }

        public MatchBuilder withResult(MatchResult result) {
            this.result = result;
            return  this;
        }

        public String getId() {
            return id;
        }

        public Organization getLocal() {
            return local;
        }

        public Organization getVisitor() {
            return visitor;
        }

        public ZonedDateTime getStartDateTime() {
            return startDateTime;
        }

        public CompetitionGroup getGroup() {
            return group;
        }

        public Integer getDay() {
            return day;
        }

        public MatchResult getResult() {
            return result;
        }

        public Competition getCompetition() {
            return competition;
        }

        public Match create() { return createNewMatch(this); }
    }
}
