package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.tttamics.configuration.RetrievalProperties;
import org.tttamics.scrapper.importer.domain.service.ResultImportService;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
@EnableConfigurationProperties({RetrievalProperties.class})
public class ResultsImporter implements CommandLineRunner {

    @Inject
    private ResultImportService resultImportService;

    public static void main(String[] args) {
        SpringApplication.run(ResultsImporter.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        resultImportService.doImport();
    }
}
