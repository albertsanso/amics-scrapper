package org.tttamics.scrapper.retrieval.federacio.barcelonesa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tttamics.scrapper.retrieval.ResultsWriter;
import org.tttamics.scrapper.retrieval.domain.model.MatchRecord;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BcnResultsCrawler {

    private static final String BASE_URL = "http://www.rtbtt.com";

    private BcnResultsScratcher bcnResultsScratcher;

    private ResultsWriter resultsWriter;

    @Autowired
    public BcnResultsCrawler(BcnResultsScratcher bcnResultsScratcher, @Qualifier("BcnResultsWriter") ResultsWriter resultsWriter) {
        this.bcnResultsScratcher = bcnResultsScratcher;
        this.resultsWriter = resultsWriter;
    }

    public List<SeasonUrl> getSeasons() throws IOException {

        List<SeasonUrl> seasonUrlList = new ArrayList<>();

        String url = BASE_URL;
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.select("#cssmenu > ul > li:nth-child(2) > ul > li:nth-child(3) > ul > li");
        for (Element el : els) {
            String href = el.select("a").attr("href");
            String urlString = extractUrlFromHref(href);
            String season = el.select("a").text();
            SeasonUrl seasonUrl = new SeasonUrl(season, urlString);
            seasonUrlList.add(seasonUrl);
        }

        return seasonUrlList;
    }

    private List<SeasonUrl> filterSeasons(List<SeasonUrl> seasonUrlList, List<String> filteringSeasons) {
        return seasonUrlList.stream().filter(seasonUrl ->
                filteringSeasons.stream().anyMatch(f ->
                        f.equals(seasonUrl.getSeason()))).collect(Collectors.toList());
    }

    private String extractUrlFromHref(String href) {
        String[] part1 = href.split("javascript:loadurl\\('");
        String[] part2 = part1[1].split("','main'\\);");
        String urlString = part2[0];
        return urlString;
    }

    private List<CategoryUrl> getCategoryUrlsFromSeasonUrl(String url) throws IOException {

        List<CategoryUrl> categoryUrlList = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements rowElements = doc.select("body > div > table > tbody > tr > td > table > tbody > tr");
        for (Element rowElement : rowElements) {

            processCategoryRow(categoryUrlList, rowElement);
        }

        return categoryUrlList;
    }

    private void processCategoryRow(List<CategoryUrl> categoryUrlList, Element rowElement) {

        Element categoryElement = rowElement.selectFirst("th > div");
        if (!Objects.isNull(categoryElement)) {

            List<GroupUrl> groupUrlList = new ArrayList<>();
            Elements groupsRowElements = rowElement.select("td:nth-child(2) > div > div > a");
            for (Element groupsRowElement : groupsRowElements) {

                buildGroupUrl(groupUrlList, groupsRowElement);
            }

            String categoryName = categoryElement.text();
            CategoryUrl categoryUrl = new CategoryUrl(categoryName, groupUrlList);
            categoryUrlList.add(categoryUrl);
        }
    }

    private void buildGroupUrl(List<GroupUrl> groupUrlList, Element el2) {

        String href = el2.attr("href");
        String hrefUrl = extractUrlFromHref(href.replaceAll("\\r\\n|\\r|\\n", ""));
        String group = el2.text();
        groupUrlList.add(new GroupUrl(group, hrefUrl));
    }

    public List<MatchRecord> fetch(List<String> filteringSeasons) throws IOException {
        List<MatchRecord> matchRecordList = new ArrayList<>();

        List<SeasonUrl> seasonUrlList = getSeasons();
        seasonUrlList = filterSeasons(seasonUrlList, filteringSeasons);

        for (SeasonUrl seasonUrl : seasonUrlList) {

            List<CategoryUrl> categoryUrlList = getCategoryUrlsFromSeasonUrl(seasonUrl.getUrl());
            for (CategoryUrl categoryUrl : categoryUrlList) {

                for (GroupUrl groupUrl : categoryUrl.getUrls()) {

                    List<MatchUrl> pdfUrlsList = processGroupUrl(groupUrl);
                    for (MatchUrl matchUrl: pdfUrlsList) {

                        Map<String, String> scratchedData = bcnResultsScratcher.scratch(matchUrl);
                        if (!Objects.isNull(scratchedData)) {

                            enrichMap(scratchedData, seasonUrl, categoryUrl, groupUrl, matchUrl);
                            MatchRecord matchRecord = mapMapToMatchRecord(scratchedData);

                            resultsWriter.write(matchRecord);
                            matchRecordList.add(matchRecord);
                        }
                    }
                }
            }
        }

        return matchRecordList;
    }

    private void enrichMap(Map<String, String> scratchedData, SeasonUrl seasonUrl, CategoryUrl categoryUrl, GroupUrl groupUrl, MatchUrl matchUrl) {
        scratchedData.put(ScratchedResultField.CATEGORY, categoryUrl.getCategory());
        scratchedData.put(ScratchedResultField.GROUP, groupUrl.getGroupName());
        scratchedData.put(ScratchedResultField.SEASON, seasonUrl.getSeason());
        scratchedData.put(ScratchedResultField.DAY, ""+matchUrl.getDay());
    }

    private MatchRecord mapMapToMatchRecord(Map<String, String> scratchedData) {

        String category = scratchedData.get(ScratchedResultField.CATEGORY);
        String group = scratchedData.get(ScratchedResultField.GROUP);
        String season = scratchedData.get(ScratchedResultField.SEASON);

        String dayString = scratchedData.get(ScratchedResultField.DAY);
        int day = 0;
        try {
            day = Integer.parseInt(dayString);
        } catch (NumberFormatException nfe) {}

        String dateTimeString = scratchedData.get(ScratchedResultField.DATETIME);
        ZonedDateTime zonedDateTime = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            Date dateStr = formatter.parse(dateTimeString);
            zonedDateTime = ZonedDateTime.ofInstant(dateStr.toInstant(), ZoneId.systemDefault());

        } catch (Throwable t) {}


        String local = scratchedData.get(ScratchedResultField.LOCAL);
        String visitor = scratchedData.get(ScratchedResultField.VISITOR);

        String localScoreString = scratchedData.get(ScratchedResultField.LOCAL_SCORE);
        int localScore = 0;
        try {
            localScore = Integer.parseInt(localScoreString);
        } catch (NumberFormatException nfe) {}

        String visitorScoreString = scratchedData.get(ScratchedResultField.VISITOR_SCORE);
        int visitorScore = 0;
        try {
            visitorScore = Integer.parseInt(visitorScoreString);
        } catch (NumberFormatException nfe) {}

        String localGamesWonString = scratchedData.get(ScratchedResultField.LOCAL_GAMES);
        int localGamesWon = 0;
        try {
            localGamesWon = Integer.parseInt(localGamesWonString);
        } catch (NumberFormatException nfe) {}

        String visitorGmesWonString = scratchedData.get(ScratchedResultField.VISITOR_GAMES);
        int visitorGmesWon = 0;
        try {
            visitorGmesWon = Integer.parseInt(visitorGmesWonString);
        } catch (NumberFormatException nfe) {}

        String observations = scratchedData.get(ScratchedResultField.OBSERVATIONS);

        MatchRecord matchRecord = new MatchRecord(
                category,
                group,
                season,
                day,
                zonedDateTime,
                local,
                visitor,
                localScore,
                visitorScore,
                localGamesWon,
                visitorGmesWon,
                observations
        );
        return matchRecord;
    }

    private List<MatchUrl> processGroupUrl(GroupUrl groupUrl) throws IOException {

        List<MatchUrl> matchUrlList = new ArrayList<>();

        String url = groupUrl.getUrl();
        Document doc = Jsoup.connect(url).get();

        int currentDay = 0;
        int index=1;
        Elements rowElements = doc.select("body > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr");
        for (Element rowElement : rowElements) {

            Element candidateDayRow = rowElement.selectFirst("td.Estilo85 > div.Estilo100 > div");
            if (!Objects.isNull(candidateDayRow)) {
                String dayString = candidateDayRow.text();
                currentDay = Integer.parseInt(dayString);
                index=1;
            }

            Element candidateMatchAnchor = rowElement.selectFirst("td:nth-child(3) > div > span > a");
            if (!Objects.isNull(candidateMatchAnchor)) {
                String pdfUrl = BASE_URL+"/"+candidateMatchAnchor.attr("href");
                MatchUrl matchUrl = new MatchUrl(currentDay, pdfUrl, index);
                matchUrlList.add(matchUrl);
                index++;
            }
        }

        return matchUrlList;
    }
}
