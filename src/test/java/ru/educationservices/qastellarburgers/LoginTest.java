package ru.educationservices.qastellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.educationservices.qastellarburgers.PageObjects.LoginPage;
import ru.educationservices.qastellarburgers.PageObjects.MainPage;
import ru.educationservices.qastellarburgers.PageObjects.RegisterPage;
import ru.educationservices.qastellarburgers.PageObjects.RestorePasswordPage;
import ru.educationservices.qastellarburgers.User.User;
import ru.educationservices.qastellarburgers.User.UserAPI;

import java.util.UUID;

public class LoginTest {
    private WebDriver driver;
    private UserAPI userAPI;
    private String accessToken;
    private String email;
    private String password;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            String yandexBinary = "C:/Program Files/Yandex/YandexBrowser/Application/browser.exe";
            WebDriverManager.chromedriver()
                    .browserVersion("150")
                    .setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBinary);
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
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
        driver.quit();
    }

    @Test
    @DisplayName("Возможен вход по кнопке Войти в аккаунт на главной странице")
    public void loginFromMainPageEnterButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitProfilePageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        Assertions.assertTrue(mainPage.isCreateOrderButtonVisibility());
    }

    @Test
    @DisplayName("Вохможен вход через кнопку Личный кабинет")
    public void loginFromAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitProfilePageVisibility();
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        Assertions.assertTrue(mainPage.isCreateOrderButtonVisibility());
    }

    @Test
    @DisplayName("Вохможен вход через кнопку в форме регистрации")
    public void loginFromRegisterPageTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        MainPage mainPage = new MainPage(driver);
        Assertions.assertTrue(mainPage.isCreateOrderButtonVisibility());
    }

    @Test
    @DisplayName("Вохможен вход через кнопку в форме восстановления пароля")
    public void loginFromRestorePasswordPageTest() {
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.openRestorePasswordPage();
        restorePasswordPage.waitRestorePasswordPageVisibility();
        restorePasswordPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.loginUser(email, password);

        MainPage mainPage = new MainPage(driver);
        Assertions.assertTrue(mainPage.isCreateOrderButtonVisibility());
    }
}
