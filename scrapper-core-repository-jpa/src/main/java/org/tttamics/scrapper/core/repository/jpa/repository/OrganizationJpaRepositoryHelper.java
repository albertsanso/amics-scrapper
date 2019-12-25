package org.tttamics.scrapper.core.repository.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

@Repository
public interface OrganizationJpaRepositoryHelper extends CrudRepository<JpaOrganization, String> {
    JpaOrganization findByNameLike(String name);
}
