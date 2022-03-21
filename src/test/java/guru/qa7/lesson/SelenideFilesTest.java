package guru.qa7.lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void downloadTest() throws IOException {
        /* Использовать для скачивания сложных файлов:
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = FileDownloadMode.PROXY;
         */

        Selenide.open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File downloadedFile = $("#raw-url").download(); //скачиваем файл
        //       new FileReader(downloadedFile); - получение загруженного файла на чтение
        try (InputStream is = new FileInputStream(downloadedFile)) {       //делаем стрим для чтения файла
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8)) //читаем файл
                    .contains("Eclipse Public License - v 2.0");           //вот такая строка должна быть в файле
        }
    }

    @Test
    void uploadFile() {
        Selenide.open("https://the-internet.herokuapp.com/upload");
        Selenide.$("input[type='file").uploadFromClasspath("lesson/upload.txt");
        Selenide.$("#file-submit").click();
        Selenide.$("#uploaded-files").shouldHave(Condition.text("lesson/upload.txt"));
    }
}
