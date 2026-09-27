package ru.educationservices.qastellarburgers.PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class ForgotPasswordPage {

    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By RESTORE_PASSWORD_HEADER = By.xpath(".//h2[text()='Восстановление пароля']");
    private static final By RESTORE_PASSWORD_EMAIL = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By RESTORE_PASSWORD_RESTORE_BUTTON = By.xpath(".//button[text()='Восстановить']");
    private static final By RESTORE_PASSWORD_PASSWORD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Введите новый пароль')]");
    private static final By RESTORE_PASSWORD_CODE = By.xpath(".//label[text() = 'Введите код из письма']/../input[contains(@name, 'name')]");
    private static final By RESTORE_PASSWORD_SAVE_BUTTON = By.xpath(".//button[text()='Сохранить']");
    private static final By RESTORE_PASSWORD_ENTER_BUTTON = By.xpath(".//a[text()='Войти']");

    @Step("Ожидание загрузки страницы входа")
    public void waitRestorePasswordPageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(RESTORE_PASSWORD_HEADER));
    }

    @Step("Клик по кнопке Восстановить")
    public void clickRestorePasswordRestoreButton (){
        driver.findElement(RESTORE_PASSWORD_RESTORE_BUTTON).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickRestorePasswordEnterButton (){
        driver.findElement(RESTORE_PASSWORD_ENTER_BUTTON).click();
    }
}
