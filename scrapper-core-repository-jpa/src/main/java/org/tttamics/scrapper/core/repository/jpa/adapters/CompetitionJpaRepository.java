package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.CompetitionToJpaCompetitionMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaCompetitionToCompetitionMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.repository.CompetitionJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
public class CompetitionJpaRepository implements CompetitionRepository {

    private CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper;
    private JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper;
    private CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper;

    @Inject
    public CompetitionJpaRepository(CompetitionToJpaCompetitionMapper competitionToJpaCompetitionMapper,
                                    JpaCompetitionToCompetitionMapper jpaCompetitionToCompetitionMapper,
                                    CompetitionJpaRepositoryHelper competitionJpaRepositoryHelper) {
        this.competitionToJpaCompetitionMapper = competitionToJpaCompetitionMapper;
        this.jpaCompetitionToCompetitionMapper = jpaCompetitionToCompetitionMapper;
        this.competitionJpaRepositoryHelper = competitionJpaRepositoryHelper;
    }

    @Override
    public Competition save(Competition competition) {
        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(competition);
        competitionJpaRepositoryHelper.save(jpaCompetition);
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }

    @Override
    public void remove(Competition competition) {
        JpaCompetition jpaCompetition = competitionToJpaCompetitionMapper.apply(competition);
        competitionJpaRepositoryHelper.delete(jpaCompetition);
    }

    @Override
    public Competition findById(String id) {
        JpaCompetition jpaCompetition = competitionJpaRepositoryHelper.findById(id).get();
        if (Objects.isNull(jpaCompetition)) return null;
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }

    @Override
    public Competition findByName(String name) {
        JpaCompetition jpaCompetition = competitionJpaRepositoryHelper.findByName(name);
        if (Objects.isNull(jpaCompetition)) return null;
        return jpaCompetitionToCompetitionMapper.apply(jpaCompetition);
    }
}
