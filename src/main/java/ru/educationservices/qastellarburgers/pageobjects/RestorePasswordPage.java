package ru.educationservices.qastellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class RestorePasswordPage {

    private final WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String RESTORE_PASSWORD_PAGE_URL = "https://qa-stellarburgers.education-services.ru/forgot-password";
    private static final By RESTORE_PASSWORD_HEADER = By.xpath(".//h2[text()='Восстановление пароля']");
    private static final By ENTER_BUTTON = By.xpath(".//a[text()='Войти']");

    @Step("Открытие страницы восстановления пароля")
    public void openRestorePasswordPage() {
        driver.get(RESTORE_PASSWORD_PAGE_URL);
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitRestorePasswordPageVisibility() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(RESTORE_PASSWORD_HEADER));
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }
}
