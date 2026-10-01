package ru.educationservices.qastellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By LOGIN_HEADER = By.xpath(".//h2[text()='Вход']");
    private static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Пароль')]");
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    private static final By REGISTER_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By RESTORE_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Ожидание загрузки страницы входа")
    public void waitLoginPageVisibility() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_HEADER));
    }

    @Step("Проверка загрузки страницы входа")
    public boolean isLoginPageOpened() {
        try {
            waitLoginPageVisibility();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Логин пользователя: {email}")
    public void loginUser(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickEnterButton();
    }
}
