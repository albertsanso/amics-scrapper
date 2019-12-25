package org.tttamics.scrapper.core.domain.service.organization;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationSearchService {

    private OrganizationRepository organizationRepository;

    @Inject
    public OrganizationSearchService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization findByName(String name) {
        return organizationRepository.findByName(name);
    }
}
