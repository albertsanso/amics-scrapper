package org.tttamics.scrapper.core.domain.port;

import org.tttamics.scrapper.core.domain.model.team.Team;

public interface TeamRepository {
    Team save(Team team);
    void remove(Team team);
    Team findById(String id);
    Team findByName(String name);
    Team findByNameLike(String name);
}
