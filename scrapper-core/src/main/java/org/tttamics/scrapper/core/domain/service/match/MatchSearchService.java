package org.tttamics.scrapper.core.domain.service.match;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.core.domain.port.MatchRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class MatchSearchService {

    private MatchRepository matchRepository;

    @Inject
    public MatchSearchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findByCompetition(Competition competition) {
        return matchRepository.findByCompetition(competition);
    }
}
