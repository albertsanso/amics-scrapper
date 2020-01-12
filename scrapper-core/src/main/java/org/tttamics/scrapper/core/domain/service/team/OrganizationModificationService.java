package org.tttamics.scrapper.core.domain.service.team;

import org.tttamics.scrapper.core.domain.port.OrganizationRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationModificationService {
    private OrganizationRepository organizationRepository;

    @Inject
    public OrganizationModificationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
}
