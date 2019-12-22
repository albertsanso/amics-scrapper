package org.tttamics.scrapper.core.domain.port;

import org.tttamics.scrapper.core.domain.model.organization.Organization;

public interface OrganizationRepository {
    Organization save(Organization organization);
    void remove(Organization organization);
    Organization findById(String id);
    Organization findByName(String name);
}
