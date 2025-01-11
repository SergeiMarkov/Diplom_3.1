package browser;

import com.codeborne.selenide.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Browser {

    public static void initDriver() throws IOException {

        Properties properties = new Properties();

        File propertyFile = new File("src/main/resources/browser.properties");
        if (!propertyFile.exists()) {
            throw new FileNotFoundException("Файл browser.properties не найден.");
        }

        try {
            properties.load(new FileInputStream(propertyFile));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла свойств.", e);
        }

        String browserProperty = properties.getProperty("testBrowser");
        if (browserProperty == null || browserProperty.isBlank()) {
            throw new IllegalStateException("Свойство testBrowser не найдено в файле свойств.");
        }

        System.out.println("browserProperty = " + browserProperty);
        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                Configuration.browser = "CHROME";
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", " C:/Program Files/WebDriver/bin/yandexdriver.exe");
                Configuration.browser = "CHROME";
                break;
            default:
                throw new RuntimeException("Неизвестный браузер: " + browserProperty);
        }
    }

}