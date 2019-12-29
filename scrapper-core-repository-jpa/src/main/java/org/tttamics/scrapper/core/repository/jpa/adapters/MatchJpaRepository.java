package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.core.domain.port.MatchRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaMatchToMatchMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.MatchToJpaMatchMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;
import org.tttamics.scrapper.core.repository.jpa.repository.MatchJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@Transactional
public class MatchJpaRepository implements MatchRepository {

    private MatchToJpaMatchMapper matchToJpaMatchMapper;
    private JpaMatchToMatchMapper jpaMatchToMatchMapper;
    private MatchJpaRepositoryHelper matchJpaRepositoryHelper;

    @Inject
    public MatchJpaRepository(MatchToJpaMatchMapper matchToJpaMatchMapper,
                              JpaMatchToMatchMapper jpaMatchToMatchMapper,
                              MatchJpaRepositoryHelper matchJpaRepositoryHelper) {
        this.matchToJpaMatchMapper = matchToJpaMatchMapper;
        this.jpaMatchToMatchMapper = jpaMatchToMatchMapper;
        this.matchJpaRepositoryHelper = matchJpaRepositoryHelper;
    }

    @Override
    public Match save(Match match) {
        JpaMatch jpaMatch = matchToJpaMatchMapper.apply(match);
        matchJpaRepositoryHelper.save(jpaMatch);
        return jpaMatchToMatchMapper.apply(jpaMatch);
    }

    @Override
    public void remove(Match match) {
        JpaMatch jpaMatch = matchToJpaMatchMapper.apply(match);
        matchJpaRepositoryHelper.delete(jpaMatch);
    }

    @Override
    public Match findById(String id) {
        JpaMatch jpaMatch = matchJpaRepositoryHelper.findById(id).get();
        return jpaMatchToMatchMapper.apply(jpaMatch);
    }

    @Override
    public List<Match> findByCompetition(Competition competition) {

        List<JpaMatch> jpaMatchList = matchJpaRepositoryHelper.findByCompetitionId(competition.getId().getId());
        List<Match> matchList = new ArrayList<>();
        for (JpaMatch jpaMatch : jpaMatchList) {
            matchList.add(jpaMatchToMatchMapper.apply(jpaMatch));
        }
        return matchList;
    }
}
