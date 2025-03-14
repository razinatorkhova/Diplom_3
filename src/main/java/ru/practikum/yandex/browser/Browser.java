package ru.practikum.yandex.browser;

import com.codeborne.selenide.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser {
    public static void initDriver() throws IOException {
                  Properties properties = new Properties();
                  properties.load(new FileInputStream("src/test/resources/browser.properties"));
                  String browserProperty = properties.getProperty("testBrowser");

                  BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                Configuration.browser = "CHROME";
                break;
            case YANDEX:
                Configuration.browser = "CHROME";
                System.setProperty("webdriver.chrome.driver", "C:/Users/Razina/Downloads/yandexdriver-25.2.0.2123-win64/yandexdriver.exe");
                break;
            default:
                throw new RuntimeException("Browser undefined");
        }
    }
}
