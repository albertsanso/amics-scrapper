package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.core.domain.model.game.MatchId;
import org.tttamics.scrapper.core.domain.model.game.MatchResult;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.function.Function;

@Named
public class JpaMatchToMatchMapper implements Function<JpaMatch, Match> {

    private JpaTeamToTeamMapper jpaTeamToTeamMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;

    @Inject
    public JpaMatchToMatchMapper(@Lazy JpaTeamToTeamMapper jpaTeamToTeamMapper, @Lazy JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper) {
        this.jpaTeamToTeamMapper = jpaTeamToTeamMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
    }

    @Override
    public Match apply(JpaMatch jpaMatch) {
        return Match.builder(jpaCompetitionToCompetitionMapper.apply(jpaMatch.getCompetition()),
                    jpaTeamToTeamMapper.apply(jpaMatch.getLocal()),
                    jpaTeamToTeamMapper.apply(jpaMatch.getVisitor())
                )
                .withId(MatchId.of(jpaMatch.getId()))
                .withStartDateTime(ZonedDateTime.parse(jpaMatch.getStartDateTime()))
                .withGroup(CompetitionGroup.createCompetitionGroup(jpaMatch.getGroup()))
                .withDay(jpaMatch.getDay())
                .withResult(new MatchResult(jpaMatch.getLocalResultValue(), jpaMatch.getVisitorResultValue()))
                .create();
    }
}
