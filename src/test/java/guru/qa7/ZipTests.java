package guru.qa7;

import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTests {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile zf = new ZipFile("qaguru7.zip");

        // Чтение и проверка pdf-файла
        ZipEntry zipPdfCheck = zf.getEntry("junit-user-guide-5.8.2.pdf");

    }
}
