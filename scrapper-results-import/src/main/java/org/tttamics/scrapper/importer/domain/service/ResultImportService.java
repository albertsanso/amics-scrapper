package org.tttamics.scrapper.importer.domain.service;

import org.tttamics.scrapper.core.domain.model.competition.Competition;
import org.tttamics.scrapper.core.domain.model.competition.CompetitionGroup;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.core.domain.model.game.MatchResult;
import org.tttamics.scrapper.core.domain.model.team.Organization;
import org.tttamics.scrapper.core.domain.model.team.Team;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionCreationService;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionModificationService;
import org.tttamics.scrapper.core.domain.service.competition.CompetitionSearchService;
import org.tttamics.scrapper.core.domain.service.match.MatchCreationService;
import org.tttamics.scrapper.core.domain.service.team.OrganizationCreationService;
import org.tttamics.scrapper.core.domain.service.team.OrganizationSearchService;
import org.tttamics.scrapper.core.domain.service.team.TeamCreationService;
import org.tttamics.scrapper.core.domain.service.team.TeamSearchService;
import org.tttamics.scrapper.importer.domain.model.MatchRecordResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Named
public class ResultImportService {

    private ResultsReader resultsReader;

    private TeamSearchService teamSearchService;

    private TeamCreationService teamCreationService;

    private CompetitionSearchService competitionSearchService;

    private CompetitionCreationService competitionCreationService;

    private CompetitionModificationService competitionModificationService;

    private MatchCreationService matchCreationService;

    private OrganizationSearchService organizationSearchService;

    private OrganizationCreationService organizationCreationService;

    @Inject
    public ResultImportService(ResultsReader resultsReader, TeamSearchService teamSearchService, TeamCreationService teamCreationService, CompetitionSearchService competitionSearchService, CompetitionCreationService competitionCreationService, CompetitionModificationService competitionModificationService, MatchCreationService matchCreationService, OrganizationSearchService organizationSearchService, OrganizationCreationService organizationCreationService) {
        this.resultsReader = resultsReader;
        this.teamSearchService = teamSearchService;
        this.teamCreationService = teamCreationService;
        this.competitionSearchService = competitionSearchService;
        this.competitionCreationService = competitionCreationService;
        this.competitionModificationService = competitionModificationService;
        this.matchCreationService = matchCreationService;
        this.organizationSearchService = organizationSearchService;
        this.organizationCreationService = organizationCreationService;
    }

    public void doImport() throws IOException, IllegalAccessException {

        List<MatchRecordResult> matchRecordResults = resultsReader.read();
        for (MatchRecordResult matchRecordResult : matchRecordResults) {

            Organization localOrganization = resolveOrganizationByName(matchRecordResult.getLocal());
            Team local = resolveTeamByName(matchRecordResult.getLocal(), localOrganization);

            Organization visitorOrganization = resolveOrganizationByName(matchRecordResult.getVisitor());
            Team visitor = resolveTeamByName(matchRecordResult.getVisitor(), visitorOrganization);

            Competition competition = resolveCompetitionByName(matchRecordResult.getCategory());
            CompetitionGroup competitionGroup = resolveCompetitionGroupByName(matchRecordResult.getGroup(), competition);

            Match match = matchCreationService.createNewMatch(
                    competition,
                    local,
                    visitor,
                    matchRecordResult.getDateTime(),
                    competitionGroup,
                    matchRecordResult.getDay()
            );

            MatchResult matchResult = new MatchResult(matchRecordResult.getLocalScore(), matchRecordResult.getVisitorScore());
            matchCreationService.modifyMatchResult(match, matchResult);

            System.out.println("");
        }

        System.out.println("");
    }

    private String normalizeName(String teamName) {
        return teamName.strip().replaceAll("''", "");
    }

    private Organization resolveOrganizationByName(String organizationName) {

        Organization organization = organizationSearchService.findByName(organizationName);
        if (Objects.isNull(organization)) {

            organization = organizationCreationService.createNewOrganization(organizationName);
        }

        return organization;
    }

    private Team resolveTeamByName(String teamName, Organization organization) {

        teamName = normalizeName(teamName);
        Team team = teamSearchService.findByName(teamName);
        if (Objects.isNull(team)) {

            //team = teamCreationService.createNewClub(teamName);
            team = teamCreationService.createNewTeamForOrganization(teamName, organization);
        }
        return team;
    }

    private Competition resolveCompetitionByName(String competitionName) {

        competitionName = normalizeName(competitionName);
        Competition competition = competitionSearchService.findByName(competitionName);
        if (Objects.isNull(competition)) {

            competition = competitionCreationService.createNewCompetition(competitionName);
        }
        return competition;
    }

    private CompetitionGroup resolveCompetitionGroupByName(String competitionGroupName, Competition competition) {
        CompetitionGroup competitionGroup = CompetitionGroup.createCompetitionGroup(competitionGroupName);
        competitionModificationService.addGroupToCompetition(competitionGroup, competition);
        return competitionGroup;
    }
}
