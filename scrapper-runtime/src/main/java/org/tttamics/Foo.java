package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionCreationService;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionSearchService;
import org.tttamics.scrapper.core.domain.service.match.MatchCreationService;
import org.tttamics.scrapper.core.domain.service.match.MatchSearchService;
import org.tttamics.scrapper.core.domain.service.team.TeamCreationService;
import org.tttamics.scrapper.core.domain.service.team.TeamSearchService;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
public class Foo implements CommandLineRunner {

    @Inject
    private CompetitionCreationService competitionCreationService;

    @Inject
    private TeamCreationService teamCreationService;

    @Inject
    private TeamSearchService teamSearchService;

    @Inject
    private MatchCreationService matchCreationService;

    @Inject
    private CompetitionSearchService competitionSearchService;

    @Inject
    private MatchSearchService matchSearchService;

    public static void main(String[] args) {
        SpringApplication.run(Foo.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        /*
        Competition competition = competitionSearchService.findByName("my other competition");
        List<Match> matchList = matchSearchService.findByCompetition(competition);
        for (Match match : matchList) {
            System.out.println(match);
        }


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
                competition2,
                organization1,
                organization3,
                ZonedDateTime.now(),
                CompetitionGroup.createCompetitionGroup("G1"),
                1);

        matchCreationService.createNewMatch(
                competition2,
                organization1,
                organization4,
                ZonedDateTime.now(),
                CompetitionGroup.createCompetitionGroup("G1"),
                1);

        matchCreationService.createNewMatch(
                competition2,
                organization1,
                organization5,
                ZonedDateTime.now(),
                CompetitionGroup.createCompetitionGroup("G2"),
                1);

        matchCreationService.createNewMatch(
                competition2,
                organization1,
                organization6,
                ZonedDateTime.now(),
                CompetitionGroup.createCompetitionGroup("G2"),
                1);

        matchCreationService.createNewMatch(
                competition1,
                organization1,
                organization2,
                ZonedDateTime.now(),
                CompetitionGroup.createEmptyCompetitionGroup(),
                1);

         */
    }
}
