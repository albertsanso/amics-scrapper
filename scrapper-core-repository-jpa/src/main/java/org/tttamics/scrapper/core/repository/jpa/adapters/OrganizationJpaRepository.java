package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaOrganizationToOrganizationMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.OrganizationToJpaOrganizationMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;
import org.tttamics.scrapper.core.repository.jpa.repository.OrganizationJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OrganizationJpaRepository implements OrganizationRepository {

    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;
    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;
    private OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper;

    @Inject
    public OrganizationJpaRepository(OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper,
                                     JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper,
                                     OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper) {
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
        this.organizationJpaRepositoryHelper = organizationJpaRepositoryHelper;
    }

    @Override
    public Organization save(Organization organization) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organization);
        organizationJpaRepositoryHelper.save(jpaOrganization);
        return jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
    }

    @Override
    public void remove(Organization organization) {
        JpaOrganization jpaOrganization = organizationToJpaOrganizationMapper.apply(organization);
        organizationJpaRepositoryHelper.delete(jpaOrganization);
    }

    @Override
    public Organization findById(String id) {
        JpaOrganization jpaOrganization = organizationJpaRepositoryHelper.findById(id).get();
        return jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
    }

    @Override
    public Organization findByName(String name) {
        JpaOrganization jpaOrganization = organizationJpaRepositoryHelper.findByNameLike("%"+name+"%");
        if (jpaOrganization == null) return null;
        return jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
    }
}
