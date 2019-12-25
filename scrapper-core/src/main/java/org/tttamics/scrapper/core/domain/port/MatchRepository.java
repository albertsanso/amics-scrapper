package org.tttamics.scrapper.core.domain.port;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.match.Match;

import java.util.List;

public interface MatchRepository {
    Match save(Match match);
    void remove(Match match);
    Match findById(String id);
    List<Match> findByCompetition(Competition competition);
}
