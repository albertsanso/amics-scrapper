package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;

import javax.inject.Named;
import java.util.List;
import java.util.function.Function;

@Named
public class CompetitionToJpCompetitionMapper implements Function<Competition, JpaCompetition> {
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
        JpaCompetition jpaCompetition = new JpaCompetition(
                competition.getId(), competition.getName(), groupStringList);
        return jpaCompetition;
    }
}
