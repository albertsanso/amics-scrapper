package org.tttamics.scrapper.core.domain.service.team;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.port.TeamRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TeamSearchService {

    private TeamRepository teamRepository;

    @Inject
    public TeamSearchService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }
}
