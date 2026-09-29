package ru.educationservices.qastellarburgers;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.educationservices.qastellarburgers.PageObjects.*;
import ru.educationservices.qastellarburgers.User.User;
import ru.educationservices.qastellarburgers.User.UserAPI;

import java.util.UUID;

public class ProfileTest {

    private WebDriver driver;
    private UserAPI userAPI;
    private String accessToken;
    private String email;
    private String password;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.getDriver();
        userAPI = new UserAPI();

        email = UUID.randomUUID() + "@example.com";
        String name = "TestName";
        password = "TestPassword123.";
        User user = new User(email, password, name);
        userAPI.setUser(user);
        accessToken = userAPI.createUser().jsonPath().getString("accessToken");
    }

    @AfterEach
    void tearDown() {
        if (accessToken != null) {
            userAPI.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Возможен переход в личный кабинет по кнопке Личный Кабинет")
    public void openProfileByProfileButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        mainPage.waitMainPageVisibility();
        mainPage.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);

        Assertions.assertTrue(profilePage.isProfilePageOpened());
    }

    @Test
    @DisplayName("Возможен переход из личного кабинета в конструктор по кнопке Конструктор")
    public void openConstructorByConstructorButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        mainPage.waitMainPageVisibility();
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageVisibility();
        profilePage.clickConstructorButton();

        Assertions.assertTrue(mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Возможен переход из личного кабинета в конструктор по клику на лого")
    public void openConstructorByLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        mainPage.waitMainPageVisibility();
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageVisibility();
        profilePage.clickLogo();

        Assertions.assertTrue(mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Возможен выход из аккаунта по кнопке Выход в личном кабинете")
    public void exitFromProfileByExitButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        mainPage.waitMainPageVisibility();
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageVisibility();
        profilePage.clickExitButton();

        Assertions.assertTrue(loginPage.isLoginPageOpened());
    }
}
