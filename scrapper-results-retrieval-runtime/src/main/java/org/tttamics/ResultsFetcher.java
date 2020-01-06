package org.tttamics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.tttamics.configuration.RetrievalProperties;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.BcnResultsCrawler;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
@EnableConfigurationProperties({RetrievalProperties.class})
public class ResultsFetcher implements CommandLineRunner {

    @Autowired
    private BcnResultsCrawler bcnResultsCrawler;

    @Autowired
    private RetrievalProperties retrievalProperties;

    public static void main(String[] args) {
        SpringApplication.run(ResultsFetcher.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        bcnResultsCrawler.fetch(retrievalProperties.getBcn().getSeasons());
    }
}
