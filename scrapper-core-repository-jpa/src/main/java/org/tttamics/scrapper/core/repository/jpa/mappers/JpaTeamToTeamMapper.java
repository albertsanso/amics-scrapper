package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.model.team.TeamId;
import org.tttamics.scrapper.core.domain.model.team.OrganizationType;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaTeamToTeamMapper implements Function<JpaTeam, Team> {
    @Override
    public Team apply(JpaTeam jpaTeam) {
        return Team.builder(jpaTeam.getName())
                .withId(TeamId.of(jpaTeam.getId()))
                .withOrganizationType(OrganizationType.getByKey(jpaTeam.getType()))
                .withActive(jpaTeam.isActive())
                .create();
    }
}
