package ru.educationservices.qastellarburgers.PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By PROFILE_HEADER = By.xpath(".//a[text()='Профиль']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private static final By LOGO = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");
    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");

    @Step("Ожидание загрузки профиля")
    public void waitProfilePageVisibility() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_HEADER));
    }

    @Step("Проверка загрузки профиля")
    public boolean isProfilePageOpened() {
        try {
            waitProfilePageVisibility();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Клик по лого")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Клик по кнопке Выход")
    public void clickExitButton() {
        driver.findElement(EXIT_BUTTON).click();
    }

}
