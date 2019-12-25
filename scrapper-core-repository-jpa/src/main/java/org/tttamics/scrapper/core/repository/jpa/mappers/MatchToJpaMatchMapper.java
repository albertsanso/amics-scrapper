package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.domain.model.match.MatchId;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class MatchToJpaMatchMapper implements Function<Match, JpaMatch> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;

    @Inject
    public MatchToJpaMatchMapper(@Lazy OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper, @Lazy CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
    }

    @Override
    public JpaMatch apply(Match match) {
        JpaOrganization jpaOrganizationLocal = organizationToJpaOrganizationMapper.apply(match.getLocal());
        JpaOrganization jpaOrganizationVisitor = organizationToJpaOrganizationMapper.apply(match.getVisitor());
        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(match.getCompetition());

        return new JpaMatch(
                match.getId().getId(),
                jpaOrganizationLocal,
                jpaOrganizationVisitor,
                match.getStartDateTime().toString(),
                jpaCompetition,
                match.getGroup().getName(),
                match.getDay(),
                match.getResult().getLocalResultValue(),
                match.getResult().getVisitorResultValue()
        );
    }
}
