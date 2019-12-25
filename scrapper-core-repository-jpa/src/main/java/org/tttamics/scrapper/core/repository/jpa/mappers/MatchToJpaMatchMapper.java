package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class MatchToJpaMatchMapper implements Function<Match, JpaMatch> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private CompetitionToJpCompetitionMapper competitionToJpCompetitionMapper;

    @Inject
    public MatchToJpaMatchMapper(@Lazy OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper, @Lazy CompetitionToJpCompetitionMapper competitionToJpCompetitionMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.competitionToJpCompetitionMapper = competitionToJpCompetitionMapper;
    }

    @Override
    public JpaMatch apply(Match match) {
        JpaOrganization jpaOrganizationLocal = organizationToJpaOrganizationMapper.apply(match.getLocal());
        JpaOrganization jpaOrganizationVisitor = organizationToJpaOrganizationMapper.apply(match.getVisitor());
        JpaCompetition jpaCompetition = competitionToJpCompetitionMapper.apply(match.getCompetition());

        return new JpaMatch(
                match.getId(),
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
