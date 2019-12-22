package org.tttamics.scrapper.core.domain.service.competition;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class CompetitionCreationService {

    private CompetitionRepository competitionRepository;

    @Inject
    public CompetitionCreationService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition createNewCompetition(String name) {
        return createNewCompetitionWithGroups(name, null);
    }

    public Competition createNewCompetitionWithGroupNames(String name, String[] groupNames) {
        return createNewCompetitionWithGroups(name,
                Arrays.asList(groupNames)
                .stream()
                .map(group -> new CompetitionGroup(group))
                .collect(Collectors.toList())
        );
    }

    public Competition createNewCompetitionWithGroups(String name, List<CompetitionGroup> groups) {
        String id = UUID.randomUUID().toString();
        Competition competition = Competition.builder(id, name)
                .withGroups(groups)
                .create();
        competitionRepository.save(competition);
        return competition;
    }
}
