package org.tttamics.scrapper.core.domain.service.match;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.domain.model.match.MatchId;
import org.tttamics.scrapper.core.domain.model.match.MatchResult;
import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.MatchRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.UUID;

@Named
public class MatchCreationService {

    private MatchRepository matchRepository;

    @Inject
    public MatchCreationService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match createNewMatch(
            Competition competition,
            Organization local,
            Organization visitor,
            ZonedDateTime startDateTime,
            CompetitionGroup group,
            Integer day
    ) throws IllegalAccessException {

        if (!competition.isGroupInCompetition(group)) {
            throw new IllegalStateException("The group does not belong to that competition");
        }

        String id = UUID.randomUUID().toString();
        Match match = Match.builder(competition, local, visitor)
                .withId(MatchId.of(id))
                .withStartDateTime(startDateTime)
                .withGroup(group)
                .withDay(day)
                .withResult(new MatchResult(0, 0))
                .create();

        matchRepository.save(match);
        return match;
    }

    public Match modifyMatchResult(Match match, MatchResult result) {
        match.modifyMatch(
                match.getLocal(),
                match.getVisitor(),
                match.getStartDateTime(),
                match.getCompetition(),
                match.getGroup(),
                match.getDay(),
                result
        );

        matchRepository.save(match);
        return match;
    }

    public Match modifyMatchStartDateTime(Match match, ZonedDateTime startDateTime) {
        match.modifyMatch(
                match.getLocal(),
                match.getVisitor(),
                startDateTime,
                match.getCompetition(),
                match.getGroup(),
                match.getDay(),
                match.getResult()
        );

        matchRepository.save(match);
        return match;
    }
}
