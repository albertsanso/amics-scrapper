package org.tttamics.scrapper.core.repository.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;

import java.util.List;

@Repository
public interface OrganizationJpaRepositoryHelper extends CrudRepository<JpaOrganization, String> {
    JpaOrganization findByNameLike(String name);
}
