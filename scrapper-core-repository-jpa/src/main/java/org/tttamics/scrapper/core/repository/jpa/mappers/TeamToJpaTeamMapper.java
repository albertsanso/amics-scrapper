package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class TeamToJpaTeamMapper implements Function<Team, JpaTeam> {

    @Override
    public JpaTeam apply(Team team) {
        JpaTeam jpaTeam = new JpaTeam(
                team.getId().getId(),
                team.getName(),
                team.getType().toString(),
                team.isActive()
        );
        return jpaTeam;
    }
}
