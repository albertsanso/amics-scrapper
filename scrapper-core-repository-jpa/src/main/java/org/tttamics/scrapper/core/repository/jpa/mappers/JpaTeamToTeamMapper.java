package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.model.team.TeamId;
import org.tttamics.scrapper.core.domain.model.team.OrganizationType;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaTeamToTeamMapper implements Function<JpaTeam, Team> {

    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;

    @Inject
    public JpaTeamToTeamMapper(JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper) {
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
    }

    @Override
    public Team apply(JpaTeam jpaTeam) {
        Organization organization = jpaOrganizationToOrganizationMapper.apply(jpaTeam.getOrganization());

        return Team.builder(jpaTeam.getName())
                .withId(TeamId.of(jpaTeam.getId()))
                .withActive(jpaTeam.isActive())
                .withOrganization(organization)
                .create();
    }
}
