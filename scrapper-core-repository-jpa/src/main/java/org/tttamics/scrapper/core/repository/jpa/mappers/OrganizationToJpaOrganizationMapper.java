package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Named
public class OrganizationToJpaOrganizationMapper implements Function<Organization, JpaOrganization> {

    private MatchToJpaMatchMapper matchToJpaMatchMapper;

    @Inject
    public OrganizationToJpaOrganizationMapper(@Lazy MatchToJpaMatchMapper matchToJpaMatchMapper) {
        this.matchToJpaMatchMapper = matchToJpaMatchMapper;
    }

    @Override
    public JpaOrganization apply(Organization organization) {

        List<JpaMatch> jpaMatches = new ArrayList<>();
        for (Match match : organization.getMatches()) {
            jpaMatches.add(matchToJpaMatchMapper.apply(match));
        }

        JpaOrganization jpaOrganization = new JpaOrganization(
            organization.getId(), organization.getName(), organization.getType().toString(), organization.isActive(), jpaMatches);
        return jpaOrganization;
    }
}
