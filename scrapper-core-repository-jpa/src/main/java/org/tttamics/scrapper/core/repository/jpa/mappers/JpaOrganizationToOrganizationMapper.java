package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.model.organization.OrganizationId;
import org.tttamics.scrapper.core.domain.model.organization.OrganizationType;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaOrganizationToOrganizationMapper implements Function<JpaOrganization, Organization> {
    @Override
    public Organization apply(JpaOrganization jpaOrganization) {
        return Organization.builder(jpaOrganization.getName())
                .withId(OrganizationId.of(jpaOrganization.getId()))
                .withOrganizationType(OrganizationType.getByKey(jpaOrganization.getType()))
                .withActive(jpaOrganization.isActive())
                .create();
    }
}
