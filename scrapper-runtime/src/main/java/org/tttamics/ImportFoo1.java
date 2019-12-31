package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tttamics.scrapper.importer.domain.service.ResultImportService;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
public class ImportFoo1 implements CommandLineRunner {

    @Inject
    private ResultImportService resultImportService;

    public static void main(String[] args) {
        SpringApplication.run(ImportFoo1.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //resultImportService.doImport();
    }
}
