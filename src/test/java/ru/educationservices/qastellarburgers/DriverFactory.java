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

    // тут отнюдь не все необходимое, но запускал тесты на слабой машине и со слабым интернетом,
    // так что выключил все возможно
    private static ChromeOptions getCommonOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-sync");
        options.addArguments("--disable-translate");
        options.addArguments("--metrics-recording-only");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getCommonOptions());
    }

    private static WebDriver getYandexDriver() {
        String yandexBinary = "C:/Program Files/Yandex/YandexBrowser/Application/browser.exe";
        WebDriverManager.chromedriver()
                .browserVersion("150")
                .setup();
        ChromeOptions options = getCommonOptions();
        options.setBinary(yandexBinary);
        return new ChromeDriver(options);
    }
}
