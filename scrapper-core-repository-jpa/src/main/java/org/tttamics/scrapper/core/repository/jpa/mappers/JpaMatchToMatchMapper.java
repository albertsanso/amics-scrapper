package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.domain.model.match.MatchResult;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.function.Function;

@Named
public class JpaMatchToMatchMapper implements Function<JpaMatch, Match> {

    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;

    @Inject
    public JpaMatchToMatchMapper(@Lazy JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper, @Lazy JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper) {
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
    }

    @Override
    public Match apply(JpaMatch jpaMatch) {
        return Match.builder(jpaCompetitionToCompetitionMapper.apply(jpaMatch.getCompetition()),
                    jpaOrganizationToOrganizationMapper.apply(jpaMatch.getLocal()),
                    jpaOrganizationToOrganizationMapper.apply(jpaMatch.getVisitor())
                )
                .withId(jpaMatch.getId())
                .withStartDateTime(ZonedDateTime.parse(jpaMatch.getStartDateTime()))
                .withGroup(CompetitionGroup.createCompetitionGroup(jpaMatch.getGroup()))
                .withDay(jpaMatch.getDay())
                .withResult(new MatchResult(jpaMatch.getLocalResultValue(), jpaMatch.getVisitorResultValue()))
                .create();
    }
}
