package org.tttamics.scrapper.retrieval;

import com.opencsv.CSVWriter;
import org.tttamics.scrapper.retrieval.domain.model.MatchRecord;

import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

@Named
public class ResultsWriter {
    private String csvFileString;
    private CSVWriter writer;

    public void setCsvFile(String csvFileString) {
        this.csvFileString = csvFileString;
    }

    public void write(MatchRecord matchRecord) throws IOException {
        if (Objects.isNull(this.writer)) {
            File csvFile = new File(this.csvFileString);
            this.writer = new CSVWriter(new FileWriter(csvFile), ',', '"', '"', "\n");
        }
        this.writer.writeNext(matchRecord.toArray());
    }

    public void close() throws IOException {
        if (!Objects.isNull(this.writer)) {
            this.writer.close();
        }
    }
}
