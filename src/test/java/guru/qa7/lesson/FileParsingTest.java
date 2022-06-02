package guru.qa7.lesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTest {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void parsePdfTest() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = Selenide.$(byText("PDF download")).download();
        PDF parsedPdf = new PDF(pdfDownload);
        assertThat(parsedPdf.author).contains("Marc Philip");
    }

    @Test
    void parseXlsTest() throws IOException {
        try (InputStream stream = cl.getResourceAsStream("lesson/self-assesment.xlsx")) {
            XLS parsedXls = new XLS(stream);
            assertThat(parsedXls.excel.getSheetAt(0).getRow(33).getCell(0).getStringCellValue())
                    .isEqualTo("GIT");
        }
    }

    @Test
    void parseCsvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("lesson/library.csv")) {
            CSVReader readCsv = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = readCsv.readAll();
            assertThat(list)
                    .hasSize(4)
                    .contains(
                            new String[] {"Pelevin", "Chapaev_I_Pustota"},
                            new String[] {"Meirynk", "Der_Golem"},
                            new String[] {"Hawking", "The_Universe_In_A_Nutshell"}
                    );
        }
    }

    @Test
    void zipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("lesson/library.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry zipEntry;
            while ((zipEntry= zis.getNextEntry()) !=null) {
                assertThat(zipEntry.getName()).isEqualTo("library.csv");
            }
        }
    }

//    ZipFile zf = new ZipFile(new File(cl.getResource("library.zip").toURI()));
}

