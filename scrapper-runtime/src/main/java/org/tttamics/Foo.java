package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.tttamics.scrapper.core.domain.model.organization.Organization;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionCreationService;
import org.tttamics.scrapper.core.domain.service.organization.OrganizationCreationService;
import org.tttamics.scrapper.core.domain.service.organization.OrganizationFindService;

import javax.inject.Inject;

@SpringBootApplication
public class Foo implements CommandLineRunner {

    @Inject
    private CompetitionCreationService competitionCreationService;

    @Inject
    private OrganizationCreationService organizationCreationService;

    @Inject
    private OrganizationFindService organizationFindService;

    public static void main(String[] args) {
        SpringApplication.run(Foo.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        competitionCreationService.createNewCompetition("my competition");
        competitionCreationService.createNewCompetitionWithGroupNames("my other competition", new String[]{"G1", "G2", "G3"});

        Organization organization1 = organizationCreationService.createNewClub("CTT Amics Terrassa");
        Organization organization2 = organizationFindService.findByName("CTT");
        if (organization2 != null) {
            System.out.println(organization2.getName());
        }

    }
}
