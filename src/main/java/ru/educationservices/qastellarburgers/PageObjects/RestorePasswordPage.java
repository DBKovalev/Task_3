package ru.educationservices.qastellarburgers.PageObjects;

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
    private static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By RESTORE_PASSWORD_BUTTON = By.xpath(".//button[text()='Восстановить']");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Введите новый пароль')]");
    private static final By CODE_FIELD = By.xpath(".//label[text() = 'Введите код из письма']/../input[contains(@name, 'name')]");
    private static final By SAVE_BUTTON = By.xpath(".//button[text()='Сохранить']");
    private static final By ENTER_BUTTON = By.xpath(".//a[text()='Войти']");

    @Step("Открытие страницы восстановления пароля")
    public void openRestorePasswordPage (){
        driver.get(RESTORE_PASSWORD_PAGE_URL);
    }

    @Step("Ожидание загрузки страницы восстановления пороля")
    public void waitRestorePasswordPageVisibility (){
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(RESTORE_PASSWORD_HEADER));
    }

    @Step("Клик по кнопке Восстановить")
    public void clickPasswordRestoreButton (){
        driver.findElement(RESTORE_PASSWORD_BUTTON).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton (){
        driver.findElement(ENTER_BUTTON).click();
    }
}
