package guru.qa7;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipTests {

    @Test

    void zipTest() throws Exception {
        ZipFile zf = new ZipFile("src/test/resources/qaguru7.zip");

        // Чтение и проверка pdf-файла
        ZipEntry zipPdfCheck = zf.getEntry("junit-user-guide-5.8.2.pdf");
        try (InputStream pdfStream = zf.getInputStream(zipPdfCheck)) {
            PDF parsed = new PDF(pdfStream);
            assertThat(parsed.text).contains("JUnit 5 User Guide");
        }

        //Чтение и проверка xlsx-файла
        ZipEntry zipXlsxCheck = zf.getEntry("self-assesment.xlsx");
        try (InputStream xlsxStream = zf.getInputStream(zipXlsxCheck)) {
            XLS parsed = new XLS(xlsxStream);
            assertThat(parsed.excel.getSheetAt(0).getRow(33).getCell(0).getStringCellValue())
                    .isEqualTo("GIT");
        }

        //Чтение и проверка csv-файла
        ZipEntry zipCsvCheck = zf.getEntry("library.csv");
        try (InputStream csvStream = zf.getInputStream(zipCsvCheck)) {
            CSVReader readCsv = new CSVReader(new InputStreamReader(csvStream));
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
}