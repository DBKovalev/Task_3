package ru.educationservices.qastellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String MAIN_PAGE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final By CONSTRUCTOR_TEXT = By.xpath(".//h1[text()='Соберите бургер']");
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By PROFILE_BUTTON = By.xpath(".//p[text() = 'Личный Кабинет']");
    private static final By BUN_TAB = By.xpath(".//span[text()='Булки']/parent::div");
    private static final By SAUCE_TAB = By.xpath(".//span[text()='Соусы']/parent::div");
    private static final By FILLING_TAB = By.xpath(".//span[text()='Начинки']/parent::div");
    private static final By CREATE_ORDER_BUTTON = By.xpath(".//button[text() = 'Оформить заказ']");
    private static final By ACTIVE_TAB = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span");

    @Step("Открытие главной страницы")
    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitMainPageVisibility() {
        new WebDriverWait(driver, ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCTOR_TEXT));
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageOpened() {
        try {
            waitMainPageVisibility();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickEnterButton() {
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickProfileButton() {
        driver.findElement(PROFILE_BUTTON).click();
    }

    @Step("Клик по табу Булки")
    public void clickBunTab() {
        driver.findElement(BUN_TAB).click();
    }

    @Step("Клик по табу Соусы")
    public void clickSauceTab() {
        driver.findElement(SAUCE_TAB).click();
    }

    @Step("Клик по табу Начинки")
    public void clickFillingTab() {
        driver.findElement(FILLING_TAB).click();
    }

    @Step("Проверка видимости кнопки создания заказа")
    public boolean isCreateOrderButtonVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(CREATE_ORDER_BUTTON));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка, активен ли таб")
    public boolean isTabActive(String tabName) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(driver -> {
                        String activeText = driver.findElement(ACTIVE_TAB).getText();
                        return activeText.equals(tabName);
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
