package ru.educationservices.qastellarburgers.PageObjects;

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

    private static final By LOGIN_HEADER= By.xpath(".//h2[text()='Вход']");
    private static final By LOGIN_EMAIL = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By LOGIN_PASSWORD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Пароль')]");
    private static final By LOGIN_ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    private static final By LOGIN_REGISTER_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By LOGIN_RESTORE_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Ожидание загрузки страницы входа")
    public void waitEnterPageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_HEADER));
    }

    @Step("Ввод email")
    public void setLoginEmail (String email){
        driver.findElement(LOGIN_EMAIL).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setLoginPassword (String password){
        driver.findElement(LOGIN_PASSWORD).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginEnterButton (){
        driver.findElement(LOGIN_ENTER_BUTTON).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickLoginRegisterButton (){
        driver.findElement(LOGIN_REGISTER_BUTTON).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void clickLoginRestorePasswordButton (){
        driver.findElement(LOGIN_RESTORE_PASSWORD_BUTTON).click();
    }

    @Step("Логин пользователя: {email}")
    public void loginUser(String email, String password) {
        setLoginEmail(email);
        setLoginPassword(password);
        clickLoginEnterButton();
    }
}
