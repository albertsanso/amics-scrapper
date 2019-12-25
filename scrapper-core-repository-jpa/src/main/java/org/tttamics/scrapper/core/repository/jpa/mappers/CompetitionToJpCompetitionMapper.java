package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Named
public class CompetitionToJpCompetitionMapper implements Function<Competition, JpaCompetition> {

    private MatchToJpaMatchMapper matchToJpaMatchMapper;

    @Inject
    public CompetitionToJpCompetitionMapper(@Lazy MatchToJpaMatchMapper matchToJpaMatchMapper) {
        this.matchToJpaMatchMapper = matchToJpaMatchMapper;
    }

    @Override
    public JpaCompetition apply(Competition competition) {
        String groupStringList = "";
        List<CompetitionGroup> groups = competition.getGroups();
        for (CompetitionGroup competitionGroup : groups) {
            if ("".equals(groupStringList)) {
                groupStringList = competitionGroup.getName();
            }
            else {
                groupStringList += ","+competitionGroup.getName();
            }
        }

        List<JpaMatch> jpaMatches = new ArrayList<>();
        for (Match match : competition.getMatches()) {
            jpaMatches.add(matchToJpaMatchMapper.apply(match));
        }

        JpaCompetition jpaCompetition = new JpaCompetition(
                competition.getId(), competition.getName(), groupStringList, jpaMatches);
        return jpaCompetition;
    }
}
