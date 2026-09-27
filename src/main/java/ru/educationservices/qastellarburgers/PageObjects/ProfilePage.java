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

    private static final By PROFILE_HEADER = By.xpath(".//button[text()='Профиль']");
    private static final By PROFILE_CONSTRUCTOR = By.xpath(".//p[text()='Конструктор']");;
    private static final By PROFILE_LOGO = By.xpath("/div[contains(@class, 'AppHeader_header__logo')]");
    private static final By PROFILE_EXIT = By.xpath(".//button[text()='Выход']");

    @Step("Ожидание загрузки профиля")
    public void waitProfilePageVisibility (){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_HEADER));
    }

    @Step("Клик по кнопке Конструктор")
    public void clickProfileConstructorButton (){
        driver.findElement(PROFILE_CONSTRUCTOR).click();
    }

    @Step("Клик по лого")
    public void clickProfileLogo (){
        driver.findElement(PROFILE_LOGO).click();
    }

    @Step("Клик по кнопке Выход")
    public void clickProfileExitButton (){
        driver.findElement(PROFILE_EXIT).click();
    }

}
