package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionId;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;

import javax.inject.Named;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class JpaCompetitionToCompetitionMapper implements Function<JpaCompetition, Competition> {

    @Override
    public Competition apply(JpaCompetition jpaCompetition) {
        return Competition.builder(jpaCompetition.getName())
                .withId(CompetitionId.of(jpaCompetition.getId()))
                .withGroups(Arrays.asList(jpaCompetition.getGroups().split(","))
                        .stream()
                        .map(group -> CompetitionGroup.createCompetitionGroup(group))
                        .collect(Collectors.toList()))
                .create();
    }
}
