package ru.educationservices.qastellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("yandex")) {
            return getYandexDriver();
        }
        return getChromeDriver();
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getYandexDriver() {
        String yandexBinary = "C:/Program Files/Yandex/YandexBrowser/Application/browser.exe";
        WebDriverManager.chromedriver()
                .browserVersion("150")
                .setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBinary);
        return new ChromeDriver(options);
    }
}
