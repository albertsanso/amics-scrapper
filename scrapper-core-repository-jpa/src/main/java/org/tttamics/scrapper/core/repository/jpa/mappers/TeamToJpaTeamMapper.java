package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class TeamToJpaTeamMapper implements Function<Team, JpaTeam> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;

    @Inject
    public TeamToJpaTeamMapper(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
    }

    @Override
    public JpaTeam apply(Team team) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(team.getOrganization());

        return new JpaTeam(
                team.getId().getId(),
                team.getName(),
                team.isActive(),
                jpaOrganization
        );
    }
}
