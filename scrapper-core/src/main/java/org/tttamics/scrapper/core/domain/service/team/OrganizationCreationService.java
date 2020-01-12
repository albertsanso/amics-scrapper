package org.tttamics.scrapper.core.domain.service.team;

import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.domain.model.team.OrganizationId;
import org.tttamics.scrapper.core.domain.model.team.OrganizationType;
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

    public Organization createNewOrganization(String organizationName) {
        String id = UUID.randomUUID().toString();
        Organization organization = Organization.builder(organizationName)
                .withId(OrganizationId.of(id))
                .withType(OrganizationType.CLUB)
                .create();

        organizationRepository.save(organization);

        return organization;
    }
}
