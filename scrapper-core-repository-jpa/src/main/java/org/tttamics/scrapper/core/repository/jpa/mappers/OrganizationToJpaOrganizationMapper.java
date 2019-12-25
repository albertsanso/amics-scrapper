package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class OrganizationToJpaOrganizationMapper implements Function<Organization, JpaOrganization> {

    @Override
    public JpaOrganization apply(Organization organization) {
        JpaOrganization jpaOrganization = new JpaOrganization(
                organization.getId().getId(),
                organization.getName(),
                organization.getType().toString(),
                organization.isActive()
        );
        return jpaOrganization;
    }
}
