package org.tttamics.scrapper.core.domain.port;

import org.tttamics.scrapper.core.domain.model.match.Match;

public interface MatchRepository {
    Match save(Match match);
    void remove(Match match);
    Match findById(String id);
}
