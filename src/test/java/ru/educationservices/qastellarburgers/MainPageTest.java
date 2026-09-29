package ru.educationservices.qastellarburgers;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.educationservices.qastellarburgers.PageObjects.*;

public class MainPageTest {

    private WebDriver driver;

    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Возможен переход в таб Булки")
    public void openBunTabTest() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();

        mainPage.clickSauceTab();
        mainPage.isTabActive("Соусы");
        mainPage.clickBunTab();

        Assertions.assertTrue(mainPage.isTabActive("Булки"));
    }

    @Test
    @DisplayName("Возможен переход в таб Соусы")
    public void openSauceTabTest() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();

        mainPage.clickSauceTab();

        Assertions.assertTrue(mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Возможен переход в таб Начинки")
    public void openFillingTabTest() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();

        mainPage.clickFillingTab();

        Assertions.assertTrue(mainPage.isTabActive("Начинки"));
    }
}
