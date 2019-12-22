package org.tttamics.scrapper.core.domain.service.organization;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationFindService {

    private OrganizationRepository organizationRepository;

    @Inject
    public OrganizationFindService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization findByName(String name) {
        return organizationRepository.findByName(name);
    }
}
