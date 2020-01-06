package org.tttamics.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tttamics.scrapper.importer.domain.service.ResultsReader;
import org.tttamics.scrapper.retrieval.ResultsWriter;
import org.tttamics.util.YamlResourceReader;

import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class RetrievalConfiguration {

    private final RetrievalProperties retrievalProperties;

    @Autowired
    public RetrievalConfiguration(RetrievalProperties retrievalProperties) {
        this.retrievalProperties = retrievalProperties;
    }

    @Bean
    public YamlResourceReader yamlReader() {
        return new YamlResourceReader();
    }


    @Qualifier("BcnResultsWriter")
    @Bean//(name = "ResultsWriter")
    public ResultsWriter getResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(retrievalProperties.getBcn().getFile());
        return resultsWriter;
    }


    /*
    @Bean(name = "CatResultsWriter")
    public BcnResultsWriter getCatResultsWriter() throws IOException {
        BcnResultsWriter bcnResultsWriter = new BcnResultsWriter();
        bcnResultsWriter.setCsvFile(retrievalProperties.getCat());
        return bcnResultsWriter;
    }

    @Bean(name = "EspResultsWriter")
    public BcnResultsWriter getEspResultsWriter() throws IOException {
        BcnResultsWriter bcnResultsWriter = new BcnResultsWriter();
        bcnResultsWriter.setCsvFile(retrievalProperties.getEsp());
        return bcnResultsWriter;
    }
    */

    @Bean
    public ResultsReader getResultsReader() throws FileNotFoundException {
        ResultsReader reader = new ResultsReader();
        reader.setCsvFileString(retrievalProperties.getBcn().getFile());
        return  reader;
    }


}
