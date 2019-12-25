package org.tttamics.scrapper.core.domain.service.competition;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CompetitionSearchService {

    private CompetitionRepository competitionRepository;

    @Inject
    public CompetitionSearchService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition findByName(String name) {
        return competitionRepository.findByName(name);
    }
}
