package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Function;

@Named
public class MatchToJpaMatchMapper implements Function<Match, JpaMatch> {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;

    @Inject
    public MatchToJpaMatchMapper(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
    }

    @Override
    public JpaMatch apply(Match match) {
        JpaOrganization jpaOrganizationLocal = organizationToJpaOrganizationMapper.apply(match.getLocal());
        JpaOrganization jpaOrganizationVisitor = organizationToJpaOrganizationMapper.apply(match.getVisitor());

        return new JpaMatch(
                match.getId(),
                jpaOrganizationLocal,
                jpaOrganizationVisitor,
                match.getStartDateTime().toString(),
                match.getGroup().getName(),
                match.getDay(),
                match.getResult().getLocalResultValue(),
                match.getResult().getVisitorResultValue()
        );
    }
}
