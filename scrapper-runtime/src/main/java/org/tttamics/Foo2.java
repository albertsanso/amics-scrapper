package org.tttamics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.ResultsCrawler;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso", "org.tttamics" })
public class Foo2 implements CommandLineRunner {

    @Inject
    private ResultsCrawler resultsCrawler;

    public static void main(String[] args) {
        SpringApplication.run(Foo2.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        resultsCrawler.fetch();
    }
}
