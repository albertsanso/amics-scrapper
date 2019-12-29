package org.tttamics.scrapper.retrieval.federacio.catalana.nacional3a;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.retrieval.federacio.catalana.nacional3a.dto.DayResults;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Named
public class CurrentSeasonCrawler {

    public List<Match> retrieveAllMatches() throws IOException {
        List<Match> matchList = new ArrayList<>();

        List<DayResults> dayResultsList = retrieveDayResults();
        for (DayResults dayResults : dayResultsList.subList(0, 1)) {
            List<Match> dayMatchesList = processDayResults(dayResults);
            //matchList.addAll(dayMatchesList);
        }
        return matchList;
    }

    private List<DayResults> retrieveDayResults() throws IOException {
        List<DayResults> dayResultsList = new ArrayList<>();

        String url = "https://www.fctt.cat/competicio/tercera-estatal-masc/lliga-de-tercera-divisio-estatal-grups-catalans";
        Document doc = Jsoup.connect(url).get();
        Elements allResultsPerDay = doc.select("body > div.uk-container.uk-container-center > div > div > main > article > div:nth-child(3) > ul > li:nth-child(6) > ul > li");
        for (Element resultsPerDayRow : allResultsPerDay) {

            Elements days = resultsPerDayRow.select("a");
            for (Element day : days) {

                String pdfHref = day.attr("href");
                String dayAsLabel = day.text();
                Integer dayNumber = Integer.parseInt(dayAsLabel);
                DayResults dayResults = new DayResults(pdfHref, dayNumber, dayAsLabel);
                dayResultsList.add(dayResults);
            }
        }
        return dayResultsList;
    }

    private List<Match> processDayResults(DayResults dayResults) throws IOException {

        URL pdfUrl = new URL(dayResults.getPdfUrl());
        String textDocument = downloadPdfAndExtractText(pdfUrl);
        System.out.println(textDocument);
        System.out.println("-----------------------");

        //Match.builder(Competition.builder("").create(), Organization.builder().create())
        return null;
    }

    private String downloadPdfAndExtractText(URL pdfUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) pdfUrl.openConnection();
        InputStream is = connection.getInputStream();
        PDDocument pddDocument = PDDocument.load(is);

        PDFTextStripper textStripper = new PDFTextStripper();
        String textDocument = textStripper.getText(pddDocument);
        pddDocument.close();
        is.close();

        return textDocument;
    }

    public static void main(String[] args) throws IOException {
        List<Match> matchList = new CurrentSeasonCrawler().retrieveAllMatches();
    }
}
