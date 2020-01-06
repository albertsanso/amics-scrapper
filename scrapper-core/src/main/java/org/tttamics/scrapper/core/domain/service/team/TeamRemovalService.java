package org.tttamics.scrapper.core.domain.service.team;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.port.TeamRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TeamRemovalService {

    private TeamRepository teamRepository;

    @Inject
    public TeamRemovalService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void removeTeam(Team team) {
        team.remove();
    }

    public void removeTeamByName(String name) {
        Team team = teamRepository.findByName(name);
        teamRepository.remove(team);
    }
}
