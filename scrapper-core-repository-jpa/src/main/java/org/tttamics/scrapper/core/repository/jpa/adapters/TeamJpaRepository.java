package org.tttamics.scrapper.core.repository.jpa.adapters;

import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.port.TeamRepository;
import org.tttamics.scrapper.core.repository.jpa.mappers.JpaTeamToTeamMapper;
import org.tttamics.scrapper.core.repository.jpa.mappers.TeamToJpaTeamMapper;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;
import org.tttamics.scrapper.core.repository.jpa.repository.TeamJpaRepositoryHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
public class TeamJpaRepository implements TeamRepository {

    private TeamToJpaTeamMapper teamToJpaTeamMapper;
    private JpaTeamToTeamMapper jpaTeamToTeamMapper;
    private TeamJpaRepositoryHelper teamJpaRepositoryHelper;

    @Inject
    public TeamJpaRepository(TeamToJpaTeamMapper teamToJpaTeamMapper,
                             JpaTeamToTeamMapper jpaTeamToTeamMapper,
                             TeamJpaRepositoryHelper teamJpaRepositoryHelper) {
        this.teamToJpaTeamMapper = teamToJpaTeamMapper;
        this.jpaTeamToTeamMapper = jpaTeamToTeamMapper;
        this.teamJpaRepositoryHelper = teamJpaRepositoryHelper;
    }

    @Override
    public Team save(Team team) {
        JpaTeam jpaTeam = teamToJpaTeamMapper.apply(team);
        teamJpaRepositoryHelper.save(jpaTeam);
        return jpaTeamToTeamMapper.apply(jpaTeam);
    }

    @Override
    public void remove(Team team) {
        JpaTeam jpaTeam = teamToJpaTeamMapper.apply(team);
        teamJpaRepositoryHelper.delete(jpaTeam);
    }

    @Override
    public Team findById(String id) {
        JpaTeam jpaTeam = teamJpaRepositoryHelper.findById(id).get();
        if (Objects.isNull(jpaTeam)) return null;
        return jpaTeamToTeamMapper.apply(jpaTeam);
    }

    @Override
    public Team findByName(String name) {
        JpaTeam jpaTeam = teamJpaRepositoryHelper.findByName(name);
        if (jpaTeam == null) return null;
        return jpaTeamToTeamMapper.apply(jpaTeam);
    }

    @Override
    public Team findByNameLike(String name) {
        JpaTeam jpaTeam = teamJpaRepositoryHelper.findByNameLike("%"+name+"%");
        if (jpaTeam == null) return null;
        return jpaTeamToTeamMapper.apply(jpaTeam);
    }
}
