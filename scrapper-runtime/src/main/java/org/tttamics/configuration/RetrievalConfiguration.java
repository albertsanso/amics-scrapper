package org.tttamics.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tttamics.YamlResourceReader;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.ResultsWriter;

import javax.inject.Inject;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties(RetrievalProperties.class)
public class RetrievalConfiguration {

    private final RetrievalProperties properties;

    @Inject
    public RetrievalConfiguration(RetrievalProperties properties) {
        this.properties = properties;
    }

    @Bean
    public YamlResourceReader yamlReader() {
        return new YamlResourceReader();
    }

    @Qualifier("BcnResultsWriter")
    @Bean(name = "BcnResultsWriter")
    public ResultsWriter getBcnResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(properties.getBcn());
        return resultsWriter;
    }

    @Bean(name = "CatResultsWriter")
    public ResultsWriter getCatResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(properties.getCat());
        return resultsWriter;
    }

    @Bean(name = "EspResultsWriter")
    public ResultsWriter getEspResultsWriter() throws IOException {
        ResultsWriter resultsWriter = new ResultsWriter();
        resultsWriter.setCsvFile(properties.getEsp());
        return resultsWriter;
    }
}
