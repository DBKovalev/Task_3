package ru.educationservices.qastellarburgers.PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class RegisterPage {

    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String REGISTER_PAGE_URL = "https://qa-stellarburgers.education-services.ru/register";
    private static final By REGISTER_HEADER = By.xpath(".//h2[text()='Регистрация']");
    private static final By NAME_FIELD = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Пароль')]");
    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By ENTER_BUTTON = By.xpath(".//a[text()='Войти']");
    private static final By INCORRECT_PASSWORD_ERROR = By.xpath(".//a[text()='Войти']");

    @Step("Открытие страницы регистрации")
    public void openRegisterPage (){
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitRegisterPageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_HEADER));
    }

    @Step("Ввод имени")
    public void setName (String name){
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail (String email){
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword (String password){
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton (){
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickEnterButton (){
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Регистрация пользователя: {name}")
    public void loginUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка видимости ошибки некорректного пароля")
    public boolean isIncorrectPasswordErrorVisibility (){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD_ERROR));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
