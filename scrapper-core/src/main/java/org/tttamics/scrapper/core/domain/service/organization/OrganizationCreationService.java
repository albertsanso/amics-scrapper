package org.tttamics.scrapper.core.domain.service.organization;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.model.organization.OrganizationType;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
public class OrganizationCreationService {

    private OrganizationRepository organizationRepository;

    @Inject
    public OrganizationCreationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization createNewOrganizationForType(String name, OrganizationType organizationType) {
        String id = UUID.randomUUID().toString();
        Organization organization = Organization.builder(id, name)
                .withActive(true)
                .withOrganizationType(organizationType)
                .create();

        organizationRepository.save(organization);

        return organization;
    }

    public Organization createNewClub(String name) {
        return createNewOrganizationForType(name, OrganizationType.CLUB);
    }

}
