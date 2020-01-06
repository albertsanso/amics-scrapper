package org.tttamics.scrapper.core.domain.service.team;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.model.team.TeamId;
import org.tttamics.scrapper.core.domain.model.team.OrganizationType;
import org.tttamics.scrapper.core.domain.port.TeamRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
public class TeamCreationService {

    private TeamRepository teamRepository;

    @Inject
    public TeamCreationService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createNewTeamForType(String name, OrganizationType organizationType) {
        String id = UUID.randomUUID().toString();
        Team team = Team.builder(name)
                .withId(TeamId.of(id))
                .withActive(true)
                .withOrganizationType(organizationType)
                .create();

        teamRepository.save(team);

        return team;
    }

    public Team createNewClub(String name) {
        return createNewTeamForType(name, OrganizationType.CLUB);
    }

}
