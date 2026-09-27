package ru.educationservices.qastellarburgers.PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class RegisterPage {

    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By REGISTER_HEADER= By.xpath(".//h2[text()='Регистрация']");
    private static final By REGISTER_NAME = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private static final By REGISTER_EMAIL = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By REGISTER_PASSWORD = By.xpath(".//label[text() = 'Пароль']/../input[contains(@name, 'Пароль')]");
    private static final By REGISTER_REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By REGISTER_ENTER_BUTTON = By.xpath(".//a[text()='Войти']");

    @Step("Ожидание загрузки страницы регистрации")
    public void waitRegisterPageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_HEADER));
    }

    @Step("Ввод имени")
    public void setRegisterName (String name){
        driver.findElement(REGISTER_NAME).sendKeys(name);
    }

    @Step("Ввод email")
    public void setRegisterEmail (String email){
        driver.findElement(REGISTER_EMAIL).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setRegisterPassword (String password){
        driver.findElement(REGISTER_PASSWORD).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterRegisterButton (){
        driver.findElement(REGISTER_REGISTER_BUTTON).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginRegisterButton (){
        driver.findElement(REGISTER_ENTER_BUTTON).click();
    }

    @Step("Регистрация пользователя: {name}")
    public void loginUser(String name, String email, String password) {
        setRegisterName(name);
        setRegisterEmail(email);
        setRegisterPassword(password);
        clickRegisterRegisterButton();
    }
}
