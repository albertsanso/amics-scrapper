package org.tttamics.scrapper.retrieval.federacio.barcelonesa;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto.MatchUrl;
import org.tttamics.scrapper.retrieval.federacio.barcelonesa.dto.ScratchedResultField;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Named
public class ResultsScratcher {
    public Map<String, String> scratch(MatchUrl matchUrl) throws IOException {
        Map<String, String> scratchedData = new HashMap<>();

        try {
            String pdfText = getTextString(matchUrl);
            String lines[] = pdfText.split("\\r?\\n");

            System.out.println("-------------------------------------------------------------------");
            System.out.println(pdfText);
            System.out.println("----------------> " + pdfText.length());

            if (pdfText.length() == 0) return null;
            if (lines.length <= 1) return null;

            String strDate = lines[0].split(" ")[1];
            scratchedData.put(ScratchedResultField.DATETIME, strDate);
        /*
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yy");
            Date dateStr = formatter.parse(strDate);
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(dateStr.toInstant(), ZoneId.systemDefault());

            scratchedData.put(ScratchedResultField.DATETIME, zonedDateTime.toString());
        }
        catch (ParseException pe) {
            pe.printStackTrace();
        }
        */

            String strLocal = lines[1].split("XYZ ")[0].replace("ABC ", "");
            scratchedData.put(ScratchedResultField.LOCAL, strLocal);

            String strVisitor = lines[1].split("XYZ ")[1];
            scratchedData.put(ScratchedResultField.VISITOR, strVisitor);

            String strLocalScore = lines[13].split(" ")[0];
            scratchedData.put(ScratchedResultField.LOCAL_SCORE, strLocalScore);

            String strVisitorScore = lines[13].split(" ")[1];
            scratchedData.put(ScratchedResultField.VISITOR_SCORE, strVisitorScore);

            String strLocalGamesWon = lines[12].split(" ")[0];
            scratchedData.put(ScratchedResultField.LOCAL_GAMES, strLocalGamesWon);

            String strVisitorGamesWon = lines[12].split(" ")[1].replace("Jocs", "");
            scratchedData.put(ScratchedResultField.VISITOR_GAMES, strVisitorGamesWon);

            scratchedData.put(ScratchedResultField.OBSERVATIONS, "");
        }
        catch (Throwable t) {
            scratchedData = null;
        }

        return scratchedData;
    }

    private String getTextString(MatchUrl matchUrl) throws IOException {

        String stringUrl = matchUrl.getRecordUrl();
        stringUrl = stringUrl.replaceAll("\\\\", "/");

        System.out.println("---> URL: "+stringUrl);

        URL pdfUrl = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) pdfUrl.openConnection();

        String pdfFileInText = "";
        InputStream is = connection.getInputStream();
        PDDocument document = PDDocument.load(is);
        is.close();

        if (!document.isEncrypted()) {

            PDFTextStripper tStripper = new PDFTextStripper();
            tStripper.setStartPage(matchUrl.getIndex());
            tStripper.setEndPage(matchUrl.getIndex());
            pdfFileInText = tStripper.getText(document);
        }
        return pdfFileInText;
    }
}
