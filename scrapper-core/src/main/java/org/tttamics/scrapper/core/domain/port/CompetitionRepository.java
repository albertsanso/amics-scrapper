package org.tttamics.scrapper.core.domain.port;

import org.tttamics.scrapper.core.domain.model.competition.Competition;

public interface CompetitionRepository {
    Competition save(Competition competition);
    void remove(Competition competition);
    Competition findById(String id);
    Competition findByName(String name);
}
