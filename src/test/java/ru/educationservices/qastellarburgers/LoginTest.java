package ru.educationservices.qastellarburgers;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import ru.educationservices.qastellarburgers.pageobjects.LoginPage;
import ru.educationservices.qastellarburgers.pageobjects.MainPage;
import ru.educationservices.qastellarburgers.pageobjects.RegisterPage;
import ru.educationservices.qastellarburgers.pageobjects.RestorePasswordPage;
import ru.educationservices.qastellarburgers.user.User;
import ru.educationservices.qastellarburgers.user.UserAPI;

import java.util.UUID;

public class LoginTest {
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
    @DisplayName("Возможен вход по кнопке Войти в аккаунт на главной странице")
    public void loginByEnterButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        Assertions.assertTrue(mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Возможен вход через кнопку Личный Кабинет")
    public void loginByProfileButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitMainPageVisibility();
        mainPage.clickProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        Assertions.assertTrue(mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Возможен вход через кнопку в форме регистрации")
    public void loginFromRegisterPageTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.waitRegisterPageVisibility();
        registerPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        MainPage mainPage = new MainPage(driver);
        Assertions.assertTrue(mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Возможен вход через кнопку в форме восстановления пароля")
    public void loginFromRestorePasswordPageTest() {
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.openRestorePasswordPage();
        restorePasswordPage.waitRestorePasswordPageVisibility();
        restorePasswordPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        MainPage mainPage = new MainPage(driver);
        Assertions.assertTrue(mainPage.isCreateOrderButtonVisible());
    }
}
