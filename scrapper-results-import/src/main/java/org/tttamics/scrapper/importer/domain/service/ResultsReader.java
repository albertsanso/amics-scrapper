package org.tttamics.scrapper.importer.domain.service;

import com.opencsv.CSVReader;
import org.tttamics.scrapper.core.domain.model.game.Match;
import org.tttamics.scrapper.importer.domain.model.MatchRecordResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResultsReader {
    private String csvFileString;
    private CSVReader reader;

    public void setCsvFileString(String csvFileString) throws FileNotFoundException {
        this.csvFileString = csvFileString;
        File csvFile = new File(this.csvFileString);
        this.reader = new CSVReader(new FileReader(csvFile));
    }

    public List<MatchRecordResult> read() throws IOException {
        List<MatchRecordResult> matchList = new ArrayList<MatchRecordResult>();

        List<String[]> allLines = this.reader.readAll();
        for (String[] arrLine : allLines) {
            String category = arrLine[0];
            String group = arrLine[1];
            String season = arrLine[2];

            String strDay = arrLine[3];
            int day = Integer.parseInt(strDay);

            String strDateTime = arrLine[4];
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDateTime);

            String local = arrLine[5];
            String visitor = arrLine[6];

            String strLocalScore = arrLine[7];
            int localScore = Integer.parseInt(strLocalScore);
            String strVisitorScore = arrLine[8];
            int visitorScore = Integer.parseInt(strVisitorScore);
            String strLocalGamesWon = arrLine[9];
            int localGamesWon = Integer.parseInt(strLocalGamesWon);
            String strVisitorGamesWon = arrLine[10];
            int visitorGamesWon = Integer.parseInt(strVisitorGamesWon);
            String observations = arrLine[11];

            MatchRecordResult matchRecordResult = new MatchRecordResult(
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
                    visitorGamesWon,
                    observations
            );
            matchList.add(matchRecordResult);
        }

        return matchList;
    }
}
