package guru.qa7;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipTests {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile zf = new ZipFile("qaguru7.zip");

        // Чтение и проверка pdf-файла
 /*       ZipEntry zipPdfCheck = zf.getEntry("junit-user-guide-5.8.2.pdf");
        try (InputStream pdfStream = zf.getInputStream(zipPdfCheck)) {
            PDF parsed = new PDF(pdfStream);
            assertThat(parsed.text).contains("JUnit 5 User Guide");
  */
        //Чтение и проверка csv-файла

        }


    }
}
