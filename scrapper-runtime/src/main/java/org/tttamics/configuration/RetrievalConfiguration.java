package org.tttamics.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tttamics.YamlResourceReader;
import org.tttamics.scrapper.importer.domain.service.ResultsReader;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.ResultsWriter;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties(RetrievalProperties.class)
public class RetrievalConfiguration {

    private final RetrievalProperties retrievalProperties;

    @Inject
    public RetrievalConfiguration(RetrievalProperties retrievalProperties) {
        this.retrievalProperties = retrievalProperties;
    }

    @Bean
    public YamlResourceReader yamlReader() {
        return new YamlResourceReader();
    }

    @Qualifier("BcnResultsWriter")
    @Bean(name = "BcnResultsWriter")
    public ResultsWriter getBcnResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(retrievalProperties.getBcn());
        return resultsWriter;
    }

    @Bean(name = "CatResultsWriter")
    public ResultsWriter getCatResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(retrievalProperties.getCat());
        return resultsWriter;
    }

    @Bean(name = "EspResultsWriter")
    public ResultsWriter getEspResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(retrievalProperties.getEsp());
        return resultsWriter;
    }

    @Bean
    public ResultsReader getResultsReader() throws FileNotFoundException {
        ResultsReader reader = new ResultsReader();
        reader.setCsvFileString(retrievalProperties.getBcn());
        return  reader;
    }
}
