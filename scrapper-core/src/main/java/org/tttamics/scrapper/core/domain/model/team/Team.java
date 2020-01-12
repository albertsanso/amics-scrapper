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
    private boolean isActive;
    private List<Match> matches;
    private Organization organization;

    private Team(TeamId id, String name, boolean isActive, List<Match> matches, Organization organization) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.matches = matches;
        this.organization = organization;
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
                builder.isActive(),
                matches,
                builder.getOrganization());

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

    public boolean isActive() {
        return isActive;
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public Organization getOrganization() {
        return organization;
    }

    public void modifyTeam(String name, boolean isActive, Organization organization) {
        this.name = name;
        this.isActive = isActive;
        this.organization = organization;

        publishEvent(new TeamModifiedEvent());
    }


    public static final class TeamBuilder {

        private TeamId id;
        private String name;
        private boolean isActive;
        private List<Match> matches;
        private Organization organization;

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

        public TeamBuilder withActive(boolean active) {
            this.isActive = active;
            return this;
        }

        public TeamBuilder withMatches(List<Match> matches) {
            this.matches = matches;
            return this;
        }

        public TeamBuilder withOrganization(Organization organization) {
            this.organization = organization;
            return this;
        }

        public TeamId getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return isActive;
        }

        public List<Match> getMatches() {
            return matches;
        }

        public Organization getOrganization() {
            return organization;
        }

        public Team create() { return createNewTeam(this); }
    }
}
