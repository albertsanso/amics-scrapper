package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.CompetitionToJpCompetitionMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaCompetitionToCompetitionMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaOrganization;
import org.tttamics.scrapper.core.repository.jpa.repository.CompetitionJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class CompetitionJpaRepository implements CompetitionRepository {

    private CompetitionToJpCompetitionMapper competitionToJpCompetitionMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;
    private CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper;

    @Inject
    public CompetitionJpaRepository(CompetitionToJpCompetitionMapper competitionToJpCompetitionMapper,
                                    JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper,
                                    CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper) {
        this.competitionToJpCompetitionMapper = competitionToJpCompetitionMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
        this.competitionJpaRepositoryHelper = competitionJpaRepositoryHelper;
    }

    @Override
    public Competition save(Competition competition) {
        JpaCompetition jpaCompetition = competitionToJpCompetitionMapper.apply(competition);
        competitionJpaRepositoryHelper.save(jpaCompetition);
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }

    @Override
    public void remove(Competition competition) {
        JpaCompetition jpaCompetition = competitionToJpCompetitionMapper.apply(competition);
        competitionJpaRepositoryHelper.delete(jpaCompetition);
    }

    @Override
    public Competition findById(String id) {
        JpaCompetition jpaCompetition = competitionJpaRepositoryHelper.findById(id).get();
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }

    @Override
    public Competition findByName(String name) {
        JpaCompetition jpaCompetition = competitionJpaRepositoryHelper.findByName(name);
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }
}
