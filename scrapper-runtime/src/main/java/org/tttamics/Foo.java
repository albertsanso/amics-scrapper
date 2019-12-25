package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionCreationService;
import org.tttamics.scrapper.core.domain.service.match.MatchCreationService;
import org.tttamics.scrapper.core.domain.service.organization.OrganizationCreationService;
import org.tttamics.scrapper.core.domain.service.organization.OrganizationFindService;

import javax.inject.Inject;
import java.time.ZonedDateTime;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
public class Foo implements CommandLineRunner {

    @Inject
    private CompetitionCreationService competitionCreationService;

    @Inject
    private OrganizationCreationService organizationCreationService;

    @Inject
    private OrganizationFindService organizationFindService;

    @Inject
    private MatchCreationService matchCreationService;

    public static void main(String[] args) {
        SpringApplication.run(Foo.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Competition competition1 = competitionCreationService.createNewCompetition("my competition");
        Competition competition2 = competitionCreationService.createNewCompetitionWithGroupNames("my other competition", new String[]{"G1", "G2", "G3"});

        Organization organization1 = organizationCreationService.createNewClub("CTT Amics Terrassa");
        Organization organization2 = organizationCreationService.createNewClub("club 2");
        Organization organization3 = organizationCreationService.createNewClub("club 3");
        Organization organization4 = organizationCreationService.createNewClub("club 4");
        Organization organization5 = organizationCreationService.createNewClub("club 5");
        Organization organization6 = organizationCreationService.createNewClub("club 6");

        matchCreationService.createNewMatch(
                competition2,
                organization1,
                organization2,
                ZonedDateTime.now(),
                CompetitionGroup.createCompetitionGroup("G1"),
                1);

        matchCreationService.createNewMatch(
                competition1,
                organization1,
                organization2,
                ZonedDateTime.now(),
                CompetitionGroup.createEmptyCompetitionGroup(),
                1);
    }
}
