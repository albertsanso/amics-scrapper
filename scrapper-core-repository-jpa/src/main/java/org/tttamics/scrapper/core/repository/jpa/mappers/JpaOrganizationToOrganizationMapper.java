package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.domain.model.team.OrganizationId;
import org.tttamics.scrapper.core.domain.model.team.OrganizationType;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaOrganizationToOrganizationMapper implements Function<JpaOrganization, Organization> {
    @Override
    public Organization apply(JpaOrganization jpaOrganization) {
        return Organization.builder(jpaOrganization.getName())
                .withId(OrganizationId.of(jpaOrganization.getId()))
                .withType(OrganizationType.getByKey(jpaOrganization.getType()))
                .create();
    }
}
