package org.tttamics.scrapper.core.domain.service.organization;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationRemovalService {

    private OrganizationRepository organizationRepository;

    @Inject
    public OrganizationRemovalService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public void removeOrganization(Organization organization) {
        organization.remove();
    }

    public void removeOrganizationByName(String name) {
        Organization organization = organizationRepository.findByName(name);
        organizationRepository.remove(organization);
    }
}
