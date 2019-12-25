package org.tttamics.scrapper.core.domain.service.competition;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionId;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class CompetitionCreationService {

    private CompetitionRepository competitionRepository;

    @Inject
    public CompetitionCreationService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition createNewCompetition(String name) {
        String defaultCompetitionGroupName = CompetitionGroup.getDefaultCompetitionGroupName();
        return createNewCompetitionWithGroupNames(name, new String[] { defaultCompetitionGroupName });
    }

    public Competition createNewCompetitionWithGroupNames(String name, String[] groupNames) {
        return createNewCompetitionWithGroups(name,
                Arrays.asList(groupNames)
                .stream()
                .map(group -> CompetitionGroup.createCompetitionGroup(group))
                .collect(Collectors.toList())
        );
    }

    public Competition createNewCompetitionWithGroups(String name, List<CompetitionGroup> groups) {
        String id = UUID.randomUUID().toString();
        Competition competition = Competition.builder(name)
                .withId(CompetitionId.of(id))
                .withGroups(groups)
                .create();
        competitionRepository.save(competition);
        return competition;
    }
}
