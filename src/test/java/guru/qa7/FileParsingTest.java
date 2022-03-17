package guru.qa7;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTest {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void parsePdfTest() throws IOException {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = Selenide.$(byText("PDF download")).download();
        PDF parsedPdf = new PDF(pdfDownload);
        assertThat(parsedPdf.author).contains("Marc Philip");
    }

    @Test
    void parseXlsTest() throws IOException {
        try (InputStream stream = cl.getResourceAsStream("self-assesment.xlsx")) {
            XLS parsedXls = new XLS(stream);
        }
    }
}

