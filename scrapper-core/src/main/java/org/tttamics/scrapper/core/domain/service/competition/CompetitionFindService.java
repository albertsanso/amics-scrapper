package org.tttamics.scrapper.core.domain.service.competition;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.port.CompetitionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ReportAsSingleViolation;

@Named
public class CompetitionFindService {

    private CompetitionRepository competitionRepository;

    @Inject
    public CompetitionFindService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition findByName(String name) {
        return competitionRepository.findByName(name);
    }
}
