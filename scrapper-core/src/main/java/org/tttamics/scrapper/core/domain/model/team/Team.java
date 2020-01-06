package org.tttamics.scrapper.core.domain.model.team;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.TeamCreatedEvent;
import org.tttamics.scrapper.core.domain.event.TeamDeactivatedEvent;
import org.tttamics.scrapper.core.domain.event.TeamModifiedEvent;
import org.tttamics.scrapper.core.domain.event.TeamRemovedEvent;
import org.tttamics.scrapper.core.domain.model.game.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Team extends Entity {

    private TeamId id;
    private String name;
    private OrganizationType type;
    private boolean isActive;
    private List<Match> matches;

    private Team(TeamId id, String name, OrganizationType type, boolean isActive, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isActive = isActive;
        this.matches = matches;
    }

    public static TeamBuilder builder(String name) { return new TeamBuilder(name); }

    private static Team createNewTeam(TeamBuilder builder) {

        List<Match> matches = builder.getMatches();
        if (Objects.isNull(matches)) {
            matches = new ArrayList<>();
        }
        Team team = new Team(
                builder.getId(),
                builder.getName(),
                builder.getType(),
                builder.isActive(),
                matches);

        team.initTeamCreatedEvent();

        return team;
    }

    private void initTeamCreatedEvent() {
        publishEvent(new TeamCreatedEvent());
    }

    public Team deactivate() {
        this.isActive = false;
        triggerTeamDeactivatedEvent();
        return this;
    }

    private void triggerTeamDeactivatedEvent() { publishEvent(new TeamDeactivatedEvent()); }

    public Team remove() {
        triggerTeamRemovedEvent();
        return this;
    }

    private void triggerTeamRemovedEvent() { publishEvent(new TeamRemovedEvent()); }

    public void disableTeam() {
        this.isActive = false;
    }

    public TeamId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OrganizationType getType() {
        return type;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public void modifyOrganization(String name, OrganizationType type, boolean isActive) {
        this.name = name;
        this.type = type;
        this.isActive = isActive;

        publishEvent(new TeamModifiedEvent());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", matches=" + matches +
                '}';
    }

    public static final class TeamBuilder {

        private TeamId id;
        private String name;
        private OrganizationType type;
        private boolean isActive;
        private List<Match> matches;

        public TeamBuilder(String name) {
            this.name = name;
        }

        public TeamBuilder withId(TeamId id) {
            this.id = id;
            return this;
        }

        public TeamBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TeamBuilder withOrganizationType(OrganizationType organizationType) {
            this.type = organizationType;
            return this;
        }

        public TeamBuilder withActive(boolean active) {
            this.isActive = active;
            return this;
        }

        public TeamBuilder withMatches(List<Match> matches) {
            this.matches = matches;
            return this;
        }

        public TeamId getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public OrganizationType getType() {
            return type;
        }

        public boolean isActive() {
            return isActive;
        }

        public List<Match> getMatches() {
            return matches;
        }

        public Team create() { return createNewTeam(this); }
    }
}
