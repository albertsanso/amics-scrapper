package org.tttamics.scrapper.core.repository.jpa.mappers;

import org.springframework.context.annotation.Lazy;
import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.match.Match;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class JpaCompetitionToCompetitionMapper implements Function<JpaCompetition, Competition> {

    private JpaMatchToMatchMapper jpaMatchToMatchMapper;

    @Inject
    public JpaCompetitionToCompetitionMapper(@Lazy JpaMatchToMatchMapper jpaMatchToMatchMapper) {
        this.jpaMatchToMatchMapper = jpaMatchToMatchMapper;
    }

    @Override
    public Competition apply(JpaCompetition jpaCompetition) {

        List<JpaMatch> jpaMatches = jpaCompetition.getMatches();

        List<Match> matches = jpaMatches.stream()
                .map(jpaMatchToMatchMapper::apply)
                .collect(Collectors.toList());

        return Competition.builder(jpaCompetition.getName())
                .withId(jpaCompetition.getId())
                .withGroups(Arrays.asList(jpaCompetition.getGroups().split(","))
                        .stream()
                        .map(group -> CompetitionGroup.createCompetitionGroup(group))
                        .collect(Collectors.toList()))
                .withMatches(matches)
                .create();
    }
}
