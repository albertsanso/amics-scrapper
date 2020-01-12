package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.domain.port.OrganizationRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaOrganizationToOrganizationMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.OrganizationToJpaOrganizationMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;
import org.tttamics.scrapper.core.repository.jpa.repository.OrganizationJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
public class OrganizationJpaRepository implements OrganizationRepository {

    private OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper;
    private JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper;
    private OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper;

    @Inject
    public OrganizationJpaRepository(OrganizationJpaRepositoryHelper organizationJpaRepositoryHelper, JpaOrganizationToOrganizationMapper jpaOrganizationToOrganizationMapper, OrganizationToJpaOrganizationMapper organizationToJpaOrganizationMapper) {
        this.organizationJpaRepositoryHelper = organizationJpaRepositoryHelper;
        this.jpaOrganizationToOrganizationMapper = jpaOrganizationToOrganizationMapper;
        this.organizationToJpaOrganizationMapper = organizationToJpaOrganizationMapper;
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
        if (Objects.isNull(jpaOrganization)) return null;
        return jpaOrganizationToOrganizationMapper.apply(jpaOrganization);
    }

    @Override
    public Organization findByName(String name) {
        JpaOrganization jpaOrganization = organizationJpaRepositoryHelper.findByName(name);
        if (Objects.isNull(jpaOrganization)) return null;
        return jpaOrganizationToOrganizationMapper.apply(jpaOrganization);

    }
}
