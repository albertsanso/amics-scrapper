package org.tttamics.scrapper.core.domain.service.competition;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CompetitionModificationService {

    private CompetitionRepository competitionRepository;

    @Inject
    public CompetitionModificationService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public void addGroupToCompetition(CompetitionGroup group, Competition competition) {

        if (!competition.isGroupInCompetition(group)) {
            competition.addCompetitionGroup(group);
        }
        this.competitionRepository.save(competition);
    }


}
