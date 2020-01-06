package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class MatchToJpaMatchMapper implements Function<Match, JpaMatch> {

    private TeamToJpaTeamMapper teamToJpaTeamMapper;
    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;

    @Inject
    public MatchToJpaMatchMapper(@Lazy TeamToJpaTeamMapper teamToJpaTeamMapper, @Lazy CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper) {
        this.teamToJpaTeamMapper = teamToJpaTeamMapper;
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
    }

    @Override
    public JpaMatch apply(Match match) {
        JpaTeam jpaTeamLocal = teamToJpaTeamMapper.apply(match.getLocal());
        JpaTeam jpaTeamVisitor = teamToJpaTeamMapper.apply(match.getVisitor());
        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(match.getCompetition());

        return new JpaMatch(
                match.getId().getId(),
                jpaTeamLocal,
                jpaTeamVisitor,
                match.getStartDateTime().toString(),
                jpaCompetition,
                match.getGroup().getName(),
                match.getDay(),
                match.getResult().getLocalResultValue(),
                match.getResult().getVisitorResultValue()
        );
    }
}
