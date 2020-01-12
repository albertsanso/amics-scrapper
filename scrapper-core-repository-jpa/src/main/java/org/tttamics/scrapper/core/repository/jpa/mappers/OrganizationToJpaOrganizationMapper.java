package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class OrganizationToJpaOrganizationMapper implements Function<Organization, JpaOrganization> {
    @Override
    public JpaOrganization apply(Organization organization) {
        return new JpaOrganization(
                organization.getId().getId(),
                organization.getName(),
                organization.getType().name()
        );
    }
}
